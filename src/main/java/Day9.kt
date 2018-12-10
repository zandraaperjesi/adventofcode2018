import javolution.util.*
import java.math.BigInteger
import java.util.*

class Day9 {

    val PLAYERS = 446
    val LAST = 7152200L

    fun first() {
        val scores = FastTable<BigInteger>(Collections.nCopies(PLAYERS, BigInteger.ZERO)) //<Int>(Collections.nCopies(PLAYERS, 0))
        val circle = LinkedList<Long>()
        circle.add(0)
        var current = 1
        for (i in 1L..LAST) {
            if(i % 23L == 0L) {
                var toIncrement = scores[(i % PLAYERS).toInt()]
                toIncrement = toIncrement.add(BigInteger.valueOf(i))
                val toRemove = if (current - 9 >= 0) current - 9 else circle.size + current - 9
                val newCurrent = if (current - 8 >= 0) current - 7 else circle.size + current - 7
                toIncrement = toIncrement.add(BigInteger.valueOf(circle[toRemove]))
                scores[(i % PLAYERS).toInt()] = toIncrement
                current = newCurrent
                circle.remove(circle[toRemove])
            }else {
                if (current > circle.size) current = 1
                circle.add(current, i)
                current += 2
            }
        }
        println(scores.max())
    }

    fun second() {
        val scores = FastTable<BigInteger>(Collections.nCopies(PLAYERS, BigInteger.ZERO))
        val circle = LinkedList<Long>()
        circle.add(0)
        for (i in 1L..LAST) {
            println(i)
            if(i % 23L == 0L) {
                var toIncrement = scores[(i % PLAYERS).toInt()]
                toIncrement = toIncrement.add(BigInteger.valueOf(i))
                for (iter in 0 until 7) {
                    circle.push(circle.removeLast())
                }
                val toRemove = circle.removeLast()
                toIncrement = toIncrement.add(BigInteger.valueOf(toRemove))
                scores[(i % PLAYERS).toInt()] = toIncrement
                circle.add(circle.removeFirst())
            }else {
                circle.add(circle.removeFirst())
                circle.add(i)
            }
        }
        println(scores.max())
    }
}