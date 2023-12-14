fun main() {
    val day = "14"

    fun part1(input: List<String>): Int {
        val transposed = transpose(input)
        printInput(transposed)

        // roll the rocks
        val result = transposed.sumOf { line ->
            val length = line.length
            var freePos = 0
            line.mapIndexed { index, c ->
                when (c) {
                    'O' -> length - freePos++
                    '#' -> 0.also { freePos = index + 1 }
                    else -> 0
                }
            }.sum()
        }

        return result
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day${day}_1_test")
    val part1result = part1(testInput1)
    check(part1result == 136) { "Got $part1result" }

//    val testInput2 = readInput("Day${day}_2_test")
//    val part2result = part2(testInput2)
//    check(part2result == 2286) { "Got $part2result" }

    val input = readInput("Day${day}")
    part1(input).println()
    part2(input).println()
}
