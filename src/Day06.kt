fun main() {
    val day = "06"

    fun part1(input: List<String>): Int {
        val times = input[0].split("\\s+".toRegex()).let { it.subList(1, it.size) }.map { it.toInt() }
        val distances = input[1].split("\\s+".toRegex()).let { it.subList(1, it.size) }.map { it.toInt() }

        return times.mapIndexed { index, time ->
            val distance = distances[index]

            println("Game $index: time $time, distance $distance")

            (1 until time).filter { hold ->
                (time - hold) * hold > distance
            }.count()
        }.reduce { acc, i -> acc * i }
    }

    fun part2(input: List<String>): Int {
        val times = input[0].split("\\s+".toRegex()).let { it.subList(1, it.size) }.map { it.toLong() }
        val distances = input[1].split("\\s+".toRegex()).let { it.subList(1, it.size) }.map { it.toLong() }

        return times.mapIndexed { index, time ->
            val distance = distances[index]

            println("Game $index: time $time, distance $distance")

            (1 until time).filter { hold ->
                (time - hold) * hold > distance
            }.count()
        }.reduce { acc, i -> acc * i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day${day}_1_test")
    val part1result = part1(testInput1)
    check(part1result == 288) { "Got $part1result" }

    val testInput2 = readInput("Day${day}_2_test")
    val part2result = part2(testInput2)
    check(part2result == 71503) { "Got $part2result" }

    val input1 = readInput("Day${day}_1")
    part1(input1).println()
    val input2 = readInput("Day${day}_2")
    part2(input2).println()
}
