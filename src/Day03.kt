import kotlin.math.max
import kotlin.math.min

fun main() {
    val day = "03"

    fun hasNonDot(str: String, range: IntRange): Boolean {
        if (str.isEmpty()) return false

        val first = max(range.first, 0)
        val last = min(range.last, str.length - 1)
        return !str.substring(first..last).all { it == '.' }
    }

    fun part1(input: List<String>): Int {
        return (listOf("") + input + listOf("")).windowed(3, 1)
            .sumOf { lines3 ->
//                println("==============\n${lines3[1]}")
                val matchResults = "\\d+".toRegex().findAll(lines3[1])
                matchResults.sumOf { matchResult ->
                    val isPN = hasNonDot(lines3[0], matchResult.range.first - 1..matchResult.range.last + 1)
                            || hasNonDot(lines3[1], matchResult.range.first - 1..matchResult.range.first - 1)
                            || hasNonDot(lines3[1], matchResult.range.last + 1..matchResult.range.last + 1)
                            || hasNonDot(lines3[2], matchResult.range.first - 1..matchResult.range.last + 1)
//                    println(matchResult.value + " " + isPN)
                    if (isPN) matchResult.value.toInt() else 0
                }
            }
    }

    fun isAdjacent(starRange: IntRange, range2: IntRange): Boolean =
        starRange.first - 1 <= range2.last && starRange.last + 1 >= range2.first

    fun findAdjacentNumbers(starMR: MatchResult, lines3: List<String>): List<Int> {
        fun String.partNumbers() = "\\d+".toRegex().findAll(this)

        return (lines3[0].partNumbers().filter { isAdjacent(starMR.range, it.range) } +
                lines3[1].partNumbers().filter { isAdjacent(starMR.range, it.range) } +
                lines3[2].partNumbers().filter { isAdjacent(starMR.range, it.range) })
            .map { it.value.toInt() }
            .toList()
    }

    fun part2(input: List<String>): Int {
        return (listOf("") + input + listOf("")).windowed(3, 1)
            .sumOf { lines3 ->
                println("==============\n${lines3[0]}\n${lines3[1]}\n${lines3[2]}")
                val matchResults1star = "\\*".toRegex().findAll(lines3[1])

                matchResults1star.sumOf { mr ->
                    val adjacentNumbers: List<Int> = findAdjacentNumbers(mr, lines3)
                    println("$adjacentNumbers")

                    if (adjacentNumbers.size != 2) 0
                    else (adjacentNumbers[0] * adjacentNumbers[1])
                        .also { println("BINGO") }
                }
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day${day}_1_test")
    val part1result = part1(testInput1)
    check(part1result == 4361) { "Got $part1result" }

    val testInput2 = readInput("Day${day}_2_test")
    val part2result = part2(testInput2)
    check(part2result == 467835) { "Got $part2result" }

    val input = readInput("Day${day}")
    part1(input).println()
    part2(input).println()
}
