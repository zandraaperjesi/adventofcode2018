import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Day5 {

    var input = Scanner(File("src/day5.txt")).next()
    var removedShortened = ArrayList<Int>()

    fun reduce(starter: String) {
        var bufferStarter = starter
        var noChange = false
        while(!noChange) {
            noChange = true
            for (i in 0 until bufferStarter.length) {
                if(i == bufferStarter.length - 1) break
                if(Math.abs(bufferStarter[i].toInt() - bufferStarter[i + 1].toInt()) == 32) {
                    bufferStarter = bufferStarter.removeRange(i, i + 2)
                    noChange = false
                    break
                }
            }
        }
        removedShortened.add(bufferStarter.length)
    }


    fun getShortest() {
        var letter = 'a'
        while (letter <= 'z') {
            var reducedInput: String = input
            reduce(reducedInput.filter { c -> c != letter }.filter { c -> c != letter.toUpperCase() })
            letter ++
        }

    }

    fun second() {
        getShortest()
        Collections.sort(removedShortened)
        println(removedShortened[0])
    }

}