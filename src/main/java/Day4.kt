import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Day4 {

    fun getSleepChedules() {
        val scanner = Scanner(File("src/day4.txt"))
        val lines = ArrayList<String>()
        val sleepTimes = HashMap<Int, ArrayList<Int>>()

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine())
        }
        lines.sort()

        var id = 0
        var asleepAt = 0
        var wakeAt: Int
        for(line in lines) {
            if (line.contains("Guard")) id = line.substringAfter("#").substringBefore(" begins").toInt()

            if (line.contains("falls")) asleepAt = line.substringBefore("]").substringAfterLast(":").toInt()

            if (line.contains("wakes")){
                wakeAt = line.substringBefore("]").substringAfterLast(":").toInt()
                for(i in asleepAt until wakeAt) {
                    sleepTimes.getOrPut(id, {ArrayList()}).add(i)
                }
            }
        }

        val worstGuard = sleepTimes.maxBy { it.value.size }!!.key
        val sleptMinutes: ArrayList<Int>? = sleepTimes[worstGuard]
        var minuteFreq: MutableMap<Int, Long> = HashMap()
        println(sleptMinutes)

        for (i in sleptMinutes!!) {
            minuteFreq[i] = minuteFreq.getOrPut(i) {0} + 1
        }

        val mostMost = minuteFreq.maxBy { it.value }?.key
        println(mostMost)
        println(worstGuard)

        println(worstGuard.toLong() * mostMost!!)

        val mostFreqForGuard = HashMap<Int, Pair<Int, Long>>()

        for (guard in sleepTimes) {

            var guardFrequancies = HashMap<Int, Long>()
            for (x in guard.value) {
                guardFrequancies[x] = guardFrequancies.getOrPut(x, {0}) + 1
            }

            val minute=guardFrequancies.maxBy { it.value }
            if (minute != null) {
                mostFreqForGuard.put(guard.key, Pair(minute.key, minute.value))
            }
        }
        val mostest=mostFreqForGuard.maxBy { it.value.second }
        println(mostest)
    }
}