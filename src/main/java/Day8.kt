import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Day8 {
    val startState = ArrayList<Int>()
    var META_first = ArrayList<Int>()
    var META_second = ArrayList<Int>()

    fun getInput() {
        val scanner = Scanner(File("src/day8.txt"))
        while (scanner.hasNext()) {
            startState.add(scanner.nextInt())
        }

        returnSumInput(startState, META_second)
        println(META_second)
    }

    private fun returnHash(input: ArrayList<Int>): ArrayList<Int> {
        var currentArr = ArrayList<Int>(input.subList(2, input.lastIndex + 1))
        val subs = input[0]
        val myMeta = input[1]
        for (i in 0 until subs) {
            currentArr = returnHash(currentArr)
         }
        if(myMeta == 0) return currentArr
        META_first.addAll(ArrayList<Int>(currentArr.subList(0, myMeta)))
        return ArrayList(currentArr.subList(myMeta, currentArr.lastIndex + 1))
    }

    private fun returnSumInput(input: ArrayList<Int>, parentSum: ArrayList<Int>): ArrayList<Int> {
        val mySum = ArrayList<Int>()
        var sumBuff = 0
        var currentArr = ArrayList<Int>(input.subList(2, input.lastIndex + 1))
        val subs = input[0]
        val myMeta = input[1]
        if(subs == 0) {
            parentSum.add(currentArr.subList(0, myMeta).sum())
            return ArrayList(currentArr.subList(myMeta, currentArr.lastIndex + 1))
        }
        for (i in 0 until subs) {
            currentArr = returnSumInput(currentArr, mySum)
        }
        for(i in 0 until myMeta) {
            sumBuff += mySum.getOrElse(currentArr[i] - 1, {0})
        }
        parentSum.add(sumBuff)
        if(myMeta == 0) {
            return currentArr
        }
        return ArrayList(currentArr.subList(myMeta, currentArr.lastIndex + 1))
    }

}