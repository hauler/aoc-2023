fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val words = mapOf(
            "one" to '1',
            "two" to '2',
            "three" to '3',
            "four" to '4',
            "five" to '5',
            "six" to '6',
            "seven" to '7',
            "eight" to '8',
            "nine" to '9',
            "1" to '1',
            "2" to '2',
            "3" to '3',
            "4" to '4',
            "5" to '5',
            "6" to '6',
            "7" to '7',
            "8" to '8',
            "9" to '9',
        )

        fun firstWord(line: String): Char =
            line.findAnyOf(words.keys)!!.let { words[it.second]!! }

        fun lastWord(line: String): Char =
            line.findLastAnyOf(words.keys)!!.let { words[it.second]!! }

        return input.sumOf { line ->
            "${firstWord(line)}${lastWord(line)}".toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01")
    check(part1(testInput) == 55172)
    check(part2(testInput) == 54925)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
