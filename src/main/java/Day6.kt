import java.io.File
import java.util.*

class Day6 {
    fun getImput(): Set<Pair<Int, Int>>{
        var mappedCoords = hashSetOf<Pair<Int, Int>>()
        val scanner = Scanner(File("src/day6.txt"))
        while (scanner.hasNext()) {
            val line = scanner.nextLine().split(", ")
            mappedCoords.add(Pair(line[0].toInt(), line[1].toInt()))
        }
        return mappedCoords
    }

    fun getLargestArea() {
        val fixedPoints = getImput()

        val tillX = fixedPoints.maxBy { x -> x.first }!!.first
        val tillY = fixedPoints.maxBy { x -> x.second }!!.second
        val fromX = fixedPoints.minBy { x -> x.first }!!.first
        val fromY = fixedPoints.minBy { x -> x.second }!!.second

        val belongsTo = hashMapOf<Pair<Int, Int>, Int>()
        val edgeAreas = hashSetOf<Pair<Int, Int>>()

        for (i in fromX..tillX) {
            for (j in fromY..tillY) {
                var found = 0
                var currentPoint = Pair(0, 0)
                var min = Int.MAX_VALUE
                for (point in fixedPoints) {
                    val manhattan = Math.abs(point.first - i) + Math.abs(point.second - j)
                    if (manhattan < min) {
                        currentPoint = point
                        found = 1
                        min = manhattan
                    }
                    else if (Math.abs(point.first - i) + Math.abs(point.second - j) == min) found += 1
                }
                if (found == 1) {
                    belongsTo[currentPoint] = belongsTo.getOrPut(currentPoint, {0}) + 1
                    if (i == fromX || i == tillX || j == fromY || j == tillY) edgeAreas.add(currentPoint)
                }
            }
        }

        println(belongsTo.filter { !edgeAreas.contains(it.key) }.maxBy { it.value })
    }

    fun getSafeArea() {
        val fixedPoints = getImput()

        val tillX = fixedPoints.maxBy { x -> x.first }!!.first
        val tillY = fixedPoints.maxBy { x -> x.second }!!.second
        val fromX = fixedPoints.minBy { x -> x.first }!!.first
        val fromY = fixedPoints.minBy { x -> x.second }!!.second

        var safe = 0

        for (i in fromX..tillX) {
            for (j in fromY..tillY) {
                var distanceSum = 0
                for (point in fixedPoints) {
                    distanceSum += Math.abs(point.first - i) + Math.abs(point.second - j)
                    if (distanceSum > 10000) break

                }
                if (distanceSum < 10000) safe ++
            }
        }

        println(safe)
    }
}