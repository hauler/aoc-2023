data class Hand(val hand: String) {

    val cardsGrouped: Map<Char, Int>
    val kind: Kind
    val cardsScored: String

    init {
        cardsGrouped = hand.groupingBy { it }.eachCount()
        kind = Kind.entries.first { it.matches(cardsGrouped) }
        cardsScored = hand.map { replacements.getOrDefault(it, it) }.joinToString()
    }

    enum class Kind(val matcher: (Map<Char, Int>) -> Boolean) {
        FiveOfAKind({ it.size == 1 }),
        FourOfAKind({ it.size == 2 && listOf(4, 1) == it.values.sortedDescending() }),
        FullHouse({ it.size == 2 && listOf(3, 2) == it.values.sortedDescending() }),
        ThreeOfAKind({ it.size == 3 && listOf(3, 1, 1) == it.values.sortedDescending() }),
        TwoPair({ it.size == 3 && listOf(2, 2, 1) == it.values.sortedDescending() }),
        OnePair({ it.size == 4 }),
        HighCard({ it.size == 5 }),
        ;

        fun matches(hand: Map<Char, Int>) = matcher(hand)
    }

    companion object {
        val replacements = mapOf(
            'A' to 'E',
            'K' to 'D',
            'Q' to 'C',
            'J' to 'B',
            'T' to 'A',
        )

        val comparator: Comparator<Hand>
            get() =
                Comparator.comparing<Hand, Kind> { it.kind }.reversed()
                    .thenComparing(Comparator.comparing<Hand, String> { it.cardsScored })
    }
}

data class Hand2(val hand: String) {

    val cardsGrouped: Map<Char, Int>
    val kind: Kind
    val cardsScored: String

    init {
        cardsGrouped = hand.groupingBy { it }.eachCount()
        kind = Kind.entries.first { it.matches(cardsGrouped) }
        cardsScored = hand.map { replacements.getOrDefault(it, it) }.joinToString()
    }

    enum class Kind(val matcher: (Map<Char, Int>) -> Boolean) {
        FiveOfAKind({ it.size == 1 || (it.size == 2 && it.containsKey('J')) }),

        FourOfAKind({ (it.size == 2 && listOf(4, 1) == it.values.sortedDescending()) ||
                (it.size == 3 && listOf(3, 1, 1) == it.values.sortedDescending() && it.containsKey('J')) ||
                (it.size == 3 && listOf(2, 2, 1) == it.values.sortedDescending() && it['J'] == 2)
        }),

        FullHouse({ (it.size == 2 && listOf(3, 2) == it.values.sortedDescending()) ||
                (it.size == 3 && listOf(3, 1, 1) == it.values.sortedDescending() && it.containsKey('J')) ||
                (it.size == 3 && listOf(2, 2, 1) == it.values.sortedDescending() && it.containsKey('J'))
        }),

        ThreeOfAKind({ (it.size == 3 && listOf(3, 1, 1) == it.values.sortedDescending()) ||
                (it.size == 4 && listOf(2, 1, 1, 1) == it.values.sortedDescending() && it.containsKey('J'))
        }),

        TwoPair({ (it.size == 3 && listOf(2, 2, 1) == it.values.sortedDescending()) ||
                (it.size == 4 && listOf(2, 1, 1, 1) == it.values.sortedDescending() && it['J'] == 1)
        }),

        OnePair({ it.size == 4 || (it.size == 5 && it.containsKey('J')) }),

        HighCard({ it.size == 5 }),
        ;

        fun matches(hand: Map<Char, Int>) = matcher(hand)
    }

    companion object {
        val replacements = mapOf(
            'A' to 'E',
            'K' to 'D',
            'Q' to 'C',
            'J' to '1',
            'T' to 'A',
            '9' to '9',
            '8' to '8',
            '7' to '7',
            '6' to '6',
            '5' to '5',
            '4' to '4',
            '3' to '3',
            '2' to '2',
        )

        val comparator: Comparator<Hand2>
            get() =
                Comparator.comparing<Hand2, Kind> { it.kind }.reversed()
                    .thenComparing(Comparator.comparing<Hand2, String> { it.cardsScored })
    }
}

fun main() {
    val day = "07"

    fun part1(input: List<String>): Int {
        val result = input.map { line ->
            val parts = line.split(' ')
            Hand(parts[0]) to parts[1].toInt()
        }
            .sortedWith(Comparator.comparing(Pair<Hand, Int>::first, Hand.comparator))
            .mapIndexed { index, pair ->
                println("$index: $pair")
                pair.second * (index + 1)
            }
            .sum()
        return result
    }

    fun part2(input: List<String>): Int {
        val result = input.map { line ->
            val parts = line.split(' ')
            Hand2(parts[0]) to parts[1].toInt()
        }
            .sortedWith(Comparator.comparing(Pair<Hand2, Int>::first, Hand2.comparator))
            .mapIndexed { index, pair ->
                println("$index: $pair")
                pair.second * (index + 1)
            }
            .sum()
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day${day}_1_test")
    val part1result = part1(testInput1)
    check(part1result == 6440) { "Got $part1result" }

    val testInput2 = readInput("Day${day}_1_test")
    val part2result = part2(testInput2)
    check(part2result == 5905) { "Got $part2result" }

    val input = readInput("Day${day}")
    part1(input).println()
    part2(input).println()
}
