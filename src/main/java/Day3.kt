import java.awt.Point
import java.io.File
import java.util.*
import kotlin.collections.HashSet

class Day3 {

    fun getPlans() {
        val points = HashSet<Point>()
        val overLapped = HashSet<Point>()
        val input = File("src/day3.txt")
        val scanner = Scanner(input)
        val over = HashMap<Point,HashSet<Int>>()
        val notlapped = HashSet<Int>()
        while (scanner.hasNext()) {
            val line = scanner.nextLine()
            val ss = line.removePrefix("#").split("\\D+".toRegex()).map { it.toInt()}

            val startPoint = Point(ss[1],ss[2])

            val x = ss[3]
            val y = ss[4]
            val id= ss[0]
            var lap=0
            for (xCoord in startPoint.x until startPoint.x + x) {
                for (yCoord in startPoint.y until startPoint.y + y) {
                    val point = Point(xCoord, yCoord)
                    if(over[point]== null) over[point]= hashSetOf()
                    over[point]?.add(id)
                    if(!points.add(Point(xCoord, yCoord))) {
                        lap++
                        val list=over[point].orEmpty()
                        notlapped.removeAll(list)
                        overLapped.add(Point(xCoord, yCoord))
                    } else {
                        if(lap==0) notlapped.add(id)
                    }

                }
            }
        }
        println(notlapped)
    }

}