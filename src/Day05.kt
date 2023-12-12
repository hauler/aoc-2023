import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking
import kotlin.math.min

fun main() {
    val day = "05"

    fun part1(input: List<String>): Int {
        // load seeds
        val seeds: List<Long> = input[0].split(':').map { it.trim() }[1]
            .split(' ').map { it.toLong() }

        // load maps
        val maps: MutableList<MutableList<Range>> = ArrayList()
        val mapNames: MutableList<String> = ArrayList()
        var currentMap: MutableList<Range> = ArrayList()
        input.subList(2, input.size).forEach { line ->
            if (line.isNotBlank()) {
                if (line.endsWith(':')) {
                    currentMap = ArrayList<Range>().also { maps.add(it) }
                    mapNames.add(line)
                    println(line)
                } else {
                    val (dest, source, length) = line.split(' ').map { it.toLong() }
                    println("$source-$dest : $length")
                    currentMap.add(Range(source, dest, length))
                }
            }
        }

        println("-----------")

        return seeds.minOf { seed ->
            println("seed = $seed")
            maps.foldIndexed(seed) { i, key, map ->
                (map.firstNotNullOfOrNull { it.destinationOf(key) } ?: key)
                    .also { println("${mapNames[i]} $key -> $it") }
            }
        }.toInt()
    }

    fun part2(input: List<String>): Int {
        // load seeds
        val rawSeeds: List<Long> = input[0].split(':').map { it.trim() }[1]
            .split(' ').map { it.toLong() }
        val seedBegins = rawSeeds.mapIndexedNotNull { index, l -> l.takeIf { index % 2 == 0 } }
        val seedLengths = rawSeeds.mapIndexedNotNull { index, l -> l.takeIf { index % 2 == 1 } }

        // load maps
        val maps: MutableList<MutableList<Range>> = ArrayList()
        val mapNames: MutableList<String> = ArrayList()
        var currentMap: MutableList<Range> = ArrayList()
        input.subList(2, input.size).forEach { line ->
            if (line.isNotBlank()) {
                if (line.endsWith(':')) {
                    currentMap = ArrayList<Range>().also { maps.add(it) }
                    mapNames.add(line)
//                    println(line)
                } else {
                    val (dest, source, length) = line.split(' ').map { it.toLong() }
//                    println("$source-$dest : $length")
                    currentMap.add(Range(source, dest, length))
                }
            }
        }

        println("-----------")

        return runBlocking {
            seedBegins.zip(seedLengths).asFlow()
                .flatMapMerge(24) { (s, l) ->
                    generateSequence(s) { t ->
                        val ppp = (t - s).toDouble() / l * 100
                        System.out.format("$s $t $l: %.2f\n", ppp)
                        if (t < s + l - 1) t + 1 else null
                    }.asFlow()
                }
                .map { seed ->
//                println("seed = $seed")
                    maps.foldIndexed(seed) { i, key, map ->
                        (map.firstNotNullOfOrNull { it.destinationOf(key) } ?: key)
//                        .also { println("${mapNames[i]} $key -> $it") }
                    }
                }
                .reduce { accumulator, value -> min(accumulator, value) }
        }.toInt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day${day}_1_test")
    val part1result = part1(testInput1)
    check(part1result == 35) { "Got $part1result" }

    val testInput2 = readInput("Day${day}_1_test")
    val part2result = part2(testInput2)
    check(part2result == 46) { "Got $part2result" }

    val input = readInput("Day${day}")
    part1(input).println()
    part2(input).println()
}

data class Range(
    val sourceStart: Long,
    val destinationStart: Long,
    val length: Long,
) {
    fun destinationOf(sourceValue: Long): Long? =
        if (sourceValue >= sourceStart && sourceValue <= sourceStart + length - 1)
            sourceValue - sourceStart + destinationStart
        else
            null
}


/*
The first line has
a destination range start of 50,
a source range start of 98,
and a range length of 2

source range: 98-99
dest range: 50-51

seed number 98 corresponds to soil number 50
seed number 99 corresponds to soil number 51


Any source numbers that aren't mapped correspond to the same destination number.
So, seed number 10 corresponds to soil number 10.



find the lowest location number that corresponds to any of the initial seeds.
 */