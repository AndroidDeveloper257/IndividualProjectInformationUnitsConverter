package com.example.individualprojectinformationunitsconverter.utils

import android.util.Log
import com.example.individualprojectinformationunitsconverter.R

fun calculate(
    query: String,
    resultUnit: String
): Double? {
    try {
        var problem = query
        while (problem.startsWith(" ")) {
            problem = problem.substring(1)
        }
        var empty = ""
        problem.forEach {
            if (it == ',') empty += "."
            else empty += it
        }
        problem = empty
        empty = ""
        /**
         * all spaces in first will be removed and all commas replaced to points
         */
        var result: Double = 0.0
        var valueList = ArrayList<Double>()
        var unitList = ArrayList<String>()
        var last = 0
        for (index in problem.indices) {
            val char = problem[index]
            if (index == 0) {
                last = getLast(problem, index)
            }
            if (char != ' ') {
                /**
                 * it is not space
                 * it is one of them:
                 *      digit
                 *      point
                 *      text
                 */
                empty += char
            } else {
                if (last == DIGIT) {
                    valueList.add(empty.toDouble())
                } else {
                    unitList.add(empty)
                }
                empty = ""
                if (last == getLast(problem, index + 1)) return null
                last = getLast(problem, index + 1)
            }
            if (index == problem.length - 1) {
                if (last == DIGIT) {
                    valueList.add(empty.toDouble())
                } else {
                    unitList.add(empty)
                }
            }
        }
        valueList.forEach {
            Log.d(TAG, "calculate: $it")
        }
        unitList.forEach {
            Log.d(TAG, "calculate: $it")
        }
        if (valueList.size != unitList.size) return null
        for (index in 0 until valueList.size) {
            val k = valueList[index] * multiply(unitList[index])
//            result?.plus(valueList[index] * multiply(unitList[index], context))
            result += k
            Log.d(TAG, "calculate: $k")
            Log.d(TAG, "calculate: $result")
        }
        return getResult(result, resultUnit)
    } catch (e: java.lang.Exception) {
        Log.e(TAG, "calculate: $e")
        Log.e(TAG, "calculate: ${e.message}")
        Log.e(TAG, "calculate: ${e.printStackTrace()}")
        Log.e(TAG, "calculate: ${e.stackTrace}")
        return null
    }
}

fun getResult(result: Double, resultUnit: String): Double? {

}

fun multiply(acronym: String): Double {
    return when (acronym.uppercase()) {
        BIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 1.0")
            1.0
        }
        KILOBIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 8.0")
            1e3
        }
        MEGABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 1000.0")
            1e6
        }
        GIGABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 8000000.0")
            1e9
        }
        TERABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 8000000000.0")
            1e12
        }
        PETABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 8000000000000.0")
            1e15
        }
        BYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 8e15")
            8.0
        }
        KILOBYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 8e15")
            8e3
        }
        MEGABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 8e15")
            8e6
        }
        GIGABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 8e15")
            8e9
        }
        TERABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 8e15")
            8e12
        }
        PETABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculate returned 8e15")
            8e15
        }

        else -> {
            Log.d(TAG, "multiply: calculate returned 0.0")
            0.0
        }
    }
}

fun getLast(text: String, index: Int): Int {
    return if (text[index].isDigit()) {
        DIGIT
    } else {
        CHARACTER
    }
}
