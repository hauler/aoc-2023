import kotlin.math.min

fun main() {
    val day = "13"

    fun analyzePattern1(pattern: List<String>): Int {
        for (i in 1..<pattern.size) {
            val len = min(i, pattern.size - i)
            val bottom = pattern.subList(pattern.size - i, pattern.size - i + len).reversed()
            val top = pattern.subList(pattern.size - i - len, pattern.size - i)
            if (bottom == top)
                return pattern.size - i
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        // split patterns
        val patterns: MutableList<List<String>> = ArrayList()
        var start = 0
        var i = 0
        while (i <= input.size) {
            if (i == input.size || input[i].isBlank()) {
                patterns.add(input.subList(start, i))
                start = ++i
            } else ++i
        }

        val sum1 = patterns.sumOf { analyzePattern1(it) } * 100

        val sum2 = patterns.sumOf { analyzePattern1(transpose(it)) }

        return sum1 + sum2
    }

    fun equalWithSmudge(bottom: List<String>, top: List<String>): Boolean {
        return bottom.mapIndexed { index, bottomLine ->
            val topLine = top[index]

            bottomLine.mapIndexed { i, c ->
                if (c == topLine[i]) 0 else 1
            }.sum()
        }.sum() == 1
    }

    fun analyzePattern2(pattern: List<String>): Int {
        for (i in 1..<pattern.size) {
            val len = min(i, pattern.size - i)
            val bottom = pattern.subList(pattern.size - i, pattern.size - i + len).reversed()
            val top = pattern.subList(pattern.size - i - len, pattern.size - i)
            if (equalWithSmudge(bottom, top))
                return pattern.size - i
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        // split patterns
        val patterns: MutableList<List<String>> = ArrayList()
        var start = 0
        var i = 0
        while (i <= input.size) {
            if (i == input.size || input[i].isBlank()) {
                patterns.add(input.subList(start, i))
                start = ++i
            } else ++i
        }

        val sum1 = patterns.sumOf { analyzePattern2(it) } * 100

        val sum2 = patterns.sumOf { analyzePattern2(transpose(it)) }

        return sum1 + sum2
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day${day}_1_test")
    val part1result = part1(testInput1)
    check(part1result == 405) { "Got $part1result" }

    val testInput2 = readInput("Day${day}_1_test")
    val part2result = part2(testInput2)
    check(part2result == 400) { "Got $part2result" }

    val input = readInput("Day${day}")
    part1(input).println()
    part2(input).println()
}
