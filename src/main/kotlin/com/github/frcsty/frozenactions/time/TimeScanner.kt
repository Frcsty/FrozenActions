package com.github.frcsty.frozenactions.time

internal class TimeScanner(time: String) {
    private val time = time.toCharArray()
    private var index = 0

    operator fun hasNext(): Boolean {
        return index < time.size - 1
    }

    fun nextLong(): Long {
        return String(next(Char::isDigit)).toLong()
    }

    fun nextString(): String {
        return String(next { codePoint ->
            Character.isAlphabetic(codePoint.toInt())
        })
    }

    private fun next(condition: (Char) -> Boolean): CharArray {
        val startIndex = index
        while (++index < time.size && condition(time[index]));
        return time.copyOfRange(startIndex, index)
    }

}