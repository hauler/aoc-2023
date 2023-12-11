const val DAY = "00"

fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day${DAY}_1_test")
    val part1result = part1(testInput1)
    check(part1result == 8) { "Got $part1result" }

    val testInput2 = readInput("Day${DAY}_2_test")
    val part2result = part2(testInput2)
    check(part2result == 2286) { "Got $part2result" }

    val input = readInput("Day${DAY}")
    part1(input).println()
    part2(input).println()
}
