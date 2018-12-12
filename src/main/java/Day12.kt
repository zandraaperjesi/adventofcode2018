import java.io.File
import java.util.*

class Day12 {

    val INITIAL_STATE = "..##.#######...##.###...#..#.#.#..#.##.#.##....####..........#..#.######..####.#.#..###.##..##..#..#"
    var shifted = 0

    fun getInput(): List<String> {
        return File("src/day12.txt").readLines()
                .filter { it.endsWith("#") }
                .map { it.split(" ")[0] }
    }

    fun applyPattern () {
        val instructions = getInput()
        var sum = 0
        var currentGen = pad(LinkedList<String>(INITIAL_STATE.split("").subList(1, INITIAL_STATE.length + 1)))
        println(currentGen)
        for (i in 0 until 500) {
            val nextGen = LinkedList<String>()
            println(currentGen.joinToString(""))
            for (x in 0..currentGen.lastIndex - 4) {
                if (instructions.contains(currentGen.subList(x, x + 5).joinToString(""))) {
                    nextGen.add("#")
                    if (i == 499) {
                        sum += x + 2 - shifted
                    }
                } else {
                    nextGen.add(".")
                }
            }
            nextGen.addFirst(".")
            nextGen.addFirst(".")
            currentGen = pad(nextGen)
        }
        println(currentGen.indexOf("#"))
        println(shifted)
        println(sum)
    }

    fun pad (currentGen: LinkedList<String>):LinkedList<String> {
        for (x in 0 until 5 - currentGen.indexOf("#")) {
            currentGen.addFirst(".")
            shifted ++
        }

        for (y in 0 .. 5 - (currentGen.size - currentGen.lastIndexOf("#"))) {
            currentGen.add(".")
        }
        return currentGen
    }

}