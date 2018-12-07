import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class Day7 {
    var mapDependency = hashMapOf<Char, HashSet<Char>>()
    var allPrereqs = hashSetOf<Char>()
    var availableChars = hashSetOf<Char>()

    fun getInput() {
        val scanner = Scanner(File("src/day7.txt"))

        while(scanner.hasNext()) {
            var line = scanner.nextLine()
            val letters = Pair(line.get(5), line.get(36))
            mapDependency.getOrPut(letters.first) { hashSetOf() }.add(letters.second)
            allPrereqs.add(letters.second)
            availableChars.add(letters.first)
        }
    }

    fun first() {
        val order = ArrayList<Char>()
        getInput()
        val availableSteps = HashSet<Char>(availableChars.filter { x -> x !in allPrereqs })
        while (availableSteps.isNotEmpty()) {
            val availableAllSteps = hashSetOf<Char>()
            val currentStep = availableSteps.minBy { c -> c.toInt() }!!
            availableSteps.remove(currentStep)
            val stepsToAdd = mapDependency.getOrDefault(currentStep, hashSetOf())
            mapDependency.remove(currentStep)
            for (k in mapDependency) {
                availableAllSteps.addAll(k.value)
            }
            availableSteps.addAll(stepsToAdd.filter { x -> !order.contains(x) }.filter { x -> !availableAllSteps.contains(x) })
            println(availableSteps)
            order.add(currentStep)
        }
        println(order.joinToString(""))
    }

    fun second() {
        val order = ArrayList<Char>()
        getInput()
        val availableSteps = HashSet<Char>(availableChars.filter { x -> x !in allPrereqs })
        var currentSteps = ArrayList<Pair<Char, Int>>()
        while (availableSteps.isNotEmpty() || currentSteps.isNotEmpty()) {
            val availableAll = hashSetOf<Char>()
            while(currentSteps.size < 5 && availableSteps.isNotEmpty()) {
                val c = availableSteps.minBy { x -> x.toInt() }!!
                currentSteps.add(Pair(c, c.toInt() - 4))
                availableSteps.remove(c)
            }
            println(currentSteps)
            val currentStep = currentSteps.minBy { x -> x.second }
            val stepsToAdd = mapDependency.getOrDefault(currentStep!!.first, hashSetOf())
            mapDependency.remove(currentStep.first)
            for (k in mapDependency) {
                availableAll.addAll(k.value)
            }
            currentSteps.remove(currentStep)
            order.add(currentStep.first)
            currentSteps = ArrayList(currentSteps.map { x -> Pair(x.first, x.second - currentStep.second) })
            availableSteps.addAll(stepsToAdd.filter { x -> !order.contains(x) }.filter { x -> !availableAll.contains(x) })
        }
        println(order.joinToString(""))

    }
}