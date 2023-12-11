import kotlin.math.pow

fun main() {
    val day = "04"

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val parts = line.split(':', '|').map { it.trim() }
            val name = parts[0]
            val winning = parts[1].split("\\s+".toRegex()).mapTo(HashSet()) { it.toInt() }
            val mine = parts[2].split("\\s+".toRegex()).mapTo(HashSet()) { it.toInt() }
//            println("$name\n$winning - $mine")

            val mineWinning = mine intersect winning
//            println("$mineWinning")
            if (mineWinning.isEmpty()) 0
            else 2.0.pow(mineWinning.size - 1).toInt()
//                .also { println(it) }
        }
    }

    fun part2(input: List<String>): Int {
        fun calcScore(idx: Int): Int {
            val parts = input[idx].split(':', '|').map { it.trim() }
            val name = parts[0]
            val winning = parts[1].split("\\s+".toRegex()).mapTo(HashSet()) { it.toInt() }
            val mine = parts[2].split("\\s+".toRegex()).mapTo(HashSet()) { it.toInt() }
//            println("$name\n$winning - $mine")

            val mineWinning = mine intersect winning
//            println("$mineWinning")
            return mineWinning.size
//                .also { println(it) }
        }

        val copies = MutableList(input.size) { 1 }
        input.forEachIndexed { index, line ->
            val score = calcScore(index)
            for (i in 1..score)
                copies[index + i] += copies[index]
        }

        return copies.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day${day}_1_test")
    val part1result = part1(testInput1)
    check(part1result == 13) { "Got $part1result" }

    val testInput2 = readInput("Day${day}_1_test")
    val part2result = part2(testInput2)
    check(part2result == 30) { "Got $part2result" }

    val input = readInput("Day${day}")
    part1(input).println()
    part2(input).println()
}
