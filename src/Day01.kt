fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val first = line.first { it.isDigit() }.digitToInt()
            val last = line.last { it.isDigit() }.digitToInt()
            first * 10 + last
        }
    }

    fun part2(input: List<String>): Int {
        val words = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
        )

        fun firstWord(line: String): Pair<Int, Int>? =
            line.findAnyOf(words.keys)?.let { it.first to words[it.second]!! }

        fun lastWord(line: String): Pair<Int, Int>? =
            line.findLastAnyOf(words.keys)?.let { it.first to words[it.second]!! }

        return input.sumOf { line ->
            val firstDigit = line.withIndex().first { it.value.isDigit() }
            val lastDigit = line.withIndex().last { it.value.isDigit() }

            val firstWord = firstWord(line)
            val lastWord = lastWord(line)

            val firstValue = if (firstDigit.index < (firstWord?.first ?: Int.MAX_VALUE))
                firstDigit.value.digitToInt()
            else
                firstWord!!.second

            val lastValue = if (lastDigit.index > (lastWord?.first ?: Int.MIN_VALUE))
                lastDigit.value.digitToInt()
            else
                lastWord!!.second

            firstValue * 10 + lastValue
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
