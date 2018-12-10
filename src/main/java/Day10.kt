import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Day10 {
    var processed = ArrayList<MutableList<Int>>()
    fun getInput() {
        val scanner = Scanner(File("src/day10.txt"))
        while (scanner.hasNext()) {
            val line = scanner.nextLine().removePrefix("position=<").removeSuffix(">")
            val coords = line.substringBefore(">").replace(" ", "").split(",").map { Integer.valueOf(it) }
            val velocity = line.substringAfter("<").replace(" ", "").split(",").map { Integer.valueOf(it) }
            val both = ArrayList<Int>()
            both.addAll(coords)
            both.addAll(velocity)
            processed.add(both)
        }
    }

    fun first() {
        getInput()

        var secs = 0;

        while(true) {
            //println(processed)
            secs += 1
            for (x in processed) {
                x[0] += x[2]
                x[1] += x[3]
            }
            val offsetX = processed.minBy { it[0] }!![0]
            val maxX = processed.maxBy { it[0] }!![0]
            val offsetY = processed.minBy { it[1] }!![1]
            val maxY = processed.maxBy { it[1] }!![1]
            if (maxY - offsetY <= 9) {
                println(processed)
                println("FOUND")
                val printer = ArrayList<ArrayList<String>>()
                for (i in offsetY..maxY) {
                    println(i)
                    printer.add(ArrayList(Collections.nCopies(maxX - offsetX + 1, " ")))
                }
                for (a in printer) {
                    println(a)
                }
                for (x in processed) {
                    printer[x[1] - offsetY][x[0] - offsetX] = "#"
                }
                for (a in printer) {
                    println(a)
                }
                println(secs)
                break
            }
        }
    }
}