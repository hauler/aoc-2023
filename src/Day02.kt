const val RED = "red"
const val GREEN = "green"
const val BLUE = "blue"

val maxCounts = mapOf(
    RED to 12,
    GREEN to 13,
    BLUE to 14
)

data class Counts(
    val red: Int = 0,
    val green: Int = 0,
    val blue: Int = 0,
) {
    val power: Int get() = red * green * blue

    companion object {
        fun fromMap(map: Map<String, Int>) =
            Counts(
                red = map.getOrDefault(RED, 0),
                green = map.getOrDefault(GREEN, 0),
                blue = map.getOrDefault(BLUE, 0),
            )

        fun max(c1: Counts, c2: Counts) =
            Counts(
                red = kotlin.math.max(c1.red, c2.red),
                green = kotlin.math.max(c1.green, c2.green),
                blue = kotlin.math.max(c1.blue, c2.blue),
            )
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val parts1 = line.split(':')
            val gameId = parts1[0].substringAfterLast(' ').toInt()
            val sets = parts1[1].split(';').map { it.trim() }

            val impossible = sets.any { set ->
                set.split(',')
                    .any {
                        val numAndColor = it.trim().split(' ')
                        numAndColor[0].toInt() > (maxCounts[numAndColor[1]] ?: 0)
                    }
            }
            if (impossible) 0 else gameId
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val parts1 = line.split(':')
            val gameId = parts1[0].substringAfterLast(' ').toInt()
            val sets = parts1[1].split(';').map { it.trim() }

            sets.fold(Counts()) { counts, set ->
                val colors = set.split(',')
                    .associate {
                        val numAndColor = it.trim().split(' ')
                        numAndColor[1] to numAndColor[0].toInt()
                    }
                Counts.max(Counts.fromMap(colors), counts)
            }.power
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day02_1_test")
    val part1result = part1(testInput1)
    check(part1result == 8) { "Got $part1result" }

    val testInput2 = readInput("Day02_2_test")
    val part2result = part2(testInput2)
    check(part2result == 2286) { "Got $part2result" }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
