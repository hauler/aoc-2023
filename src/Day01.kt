fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val words = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
            "1" to "1",
            "2" to "2",
            "3" to "3",
            "4" to "4",
            "5" to "5",
            "6" to "6",
            "7" to "7",
            "8" to "8",
            "9" to "9",
        )

        return input.sumOf { line ->
            "${firstWord(line, words)}${lastWord(line, words)}".toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day01_1_test")
    check(part1(testInput1) == 142)
    val testInput2 = readInput("Day01_2_test")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
