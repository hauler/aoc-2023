import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun firstWord(line: String, wordMap: Map<String, String>): String =
    line.findAnyOf(wordMap.keys)!!.let { wordMap[it.second]!! }

fun lastWord(line: String, wordMap: Map<String, String>): String =
    line.findLastAnyOf(wordMap.keys)!!.let { wordMap[it.second]!! }

fun transpose(input: List<String>): List<String> {
    val result = Array(input[0].length) { "" }

    input.forEach { line ->
        line.forEachIndexed { index, c -> result[index] = result[index] + c }
    }

    return result.asList()
}
