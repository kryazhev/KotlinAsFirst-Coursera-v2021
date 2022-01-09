@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = n

    do {
        number /= 10
        count++
    } while (number > 0)

    return count;
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
val cache = HashMap<Int, Int>()
fun fib(n: Int): Int {
    return cache.getOrPut(n, {
        when {
            n <= 2 -> 1
            else -> fib(n-1) + fib(n-2)
        }
    })
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (n % 2 == 0) return 2
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return m
    }
    return n
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    if (n % 2 == 0) return n / 2

    var middle = (n/2) - 1
    if (middle % 2 == 0) middle--

    for (i in middle downTo 3 step 2) {
        if (n % i == 0) return i
    }

    return 1
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count = 0
    var current = x

    while (current != 1) {
        if (current % 2 == 0) {
            current /= 2
        } else {
            current = 3 * current + 1
        }
        count++
    }

    return count
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val a = max(m, n)
    val b = min(m, n)

    return a / gcd(a, b) * b;
}

fun gcd(m: Int, n: Int): Int {
    var a = max(m, n)
    var b = min(m, n)

    while (b > 0) {
        a %= b

        val temp = a
        a = b
        b = temp
    }

    return a;
}
/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if ((m % n == 0) || (n % m == 0)) return false
    if ((m % 2 == 0) && (n % 2 == 0)) return false

    val mDivisors = mutableListOf<Int>()
    val nDivisors = mutableListOf<Int>()

    for (i in 3..sqrt(m.toDouble()).toInt() step 2) {
        if (m % i == 0) mDivisors.add(i)
    }

    for (i in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % i == 0) nDivisors.add(i)
    }

    return mDivisors.intersect(nDivisors).isEmpty()
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var result = 0;
    var current = n;

    while (current > 0) {
        result *= 10
        result += current % 10
        current /= 10
    }

    return result
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val digits = digits(n)

    for (i in 0..(digits.size/2-1)) {
        if (digits.get(i) != digits.get(digits.size - 1 - i)) return false
    }

    return true
}

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val digits = digits(n)

    for (i in 0..(digits.size-2)) {
        if (digits.get(i) != digits.get(i+1)) return true
    }

    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val shortX = x % (PI * 2)
    return shortX + sinCosSeq(shortX, eps, 3)
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val shortX = x % (PI * 2)
    return 1 + sinCosSeq(shortX, eps, 2)
}

fun sinCosSeq(x: Double, eps: Double, start: Int) : Double {
    var result = 0.0
    var sub = true
    var num = start
    while (true) {
        val delta = x.pow(num) / factorial(num)
        result = if (sub) result - delta else result + delta
        sub = !sub
        num += 2
        if (abs(delta) < abs(eps)) break
    }
    return result
}
/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int  = sequenceDigit(n) {it * it}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = sequenceDigit(n) {fib(it)}

fun sequenceDigit(n: Int, generator: (n: Int) -> Int): Int {
    var current = n
    var counter = 1
    var exit = false
    var digits: MutableList<Int> = mutableListOf<Int>()

    while (!exit) {
        digits = digits(generator(counter))
        if (current - digits.size <= 0) {
            exit = true
        } else {
            current -= digits.size
        }

        counter++
    }

    return digits.get(current - 1);
}

fun digits(n: Int): MutableList<Int> {
    val result = mutableListOf<Int>()

    var number = n
    while (number > 0) {
        result.add(0, number % 10)
        number /= 10
    }

    return result
}