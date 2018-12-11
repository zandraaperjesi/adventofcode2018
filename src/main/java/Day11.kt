import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Day11 {

    val RANGE = 300
    val SERIAL_ID = 7347

//    Find the fuel cell's rack ID, which is its X coordinate plus 10.
//    Begin with a power level of the rack ID times the Y coordinate.
//    Increase the power level by the value of the grid serial number (your puzzle input).
//    Set the power level to itself multiplied by the rack ID.
//    Keep only the hundreds digit of the power level (so 12345 becomes 3; numbers with no hundreds digit become 0).
//    Subtract 5 from the power level.

    val powerGrid = ArrayList<ArrayList<Int>>()

    fun initGrid () {
        for (i in 0 until RANGE) {
            powerGrid.add(ArrayList(Collections.nCopies(RANGE, 0)))
        }
    }

    fun first() {
        initGrid()
        for (x in 0 until RANGE) {
            for (y in 0 until RANGE) {
                val rackId = x + 1 + 10
                var power = rackId * (y + 1)
                power += SERIAL_ID
                power *= rackId
                power = ((power % 1000) / 100) - 5
                powerGrid[x][y] = power
            }
        }

        val triples = HashMap<Pair<Int, Int>, Int>()

        for (i in 0 until RANGE - 2) {
            for (j in 0 until RANGE - 2) {
                triples.put(Pair(i + 1, j + 1),
                        powerGrid[i][j] + powerGrid[i][j + 1] + powerGrid[i][j + 2]
                                + powerGrid[i + 1][j] + powerGrid[i + 1][j + 1] + powerGrid[i + 1][j + 2]
                                + powerGrid[i + 2][j] + powerGrid[i + 2][j + 1] + powerGrid[i + 2][j + 2])
            }
        }

        println(triples.maxBy { it.value })
    }

    fun second() {
        initGrid()
        for (x in 0 until RANGE) {
            for (y in 0 until RANGE) {
                val rackId = x + 1 + 10
                var power = rackId * (y + 1)
                power += SERIAL_ID
                power *= rackId
                power = ((power % 1000) / 100) - 5
                powerGrid[x][y] = power
            }
        }

        val triples = HashMap<Pair<Int, Int>, Int>()
        val sizeMap = HashMap<Int, Int>()
        for (x in 0 until RANGE) {
            for (i in 0 until RANGE - x) {
                for (j in 0 until RANGE - x) {
                    var sum = 0
                    for (k in i until i + x) {
                        for (l in j until j + x) {
                            sum += powerGrid[k][l]
                        }
                    }
                    if (triples.getOrDefault(Pair(i + 1, j + 1), 0) < sum) {
                        triples[Pair(i + 1, j + 1)] = sum
                        sizeMap[sum] = x
                    }
                }
            }
        }

        val maxVal = triples.maxBy { it.value }
        println(maxVal)
        println(sizeMap[maxVal!!.value])
    }

}