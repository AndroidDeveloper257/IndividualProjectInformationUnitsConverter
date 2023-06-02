package com.example.individualprojectinformationunitsconverter.utils

import android.util.Log

fun calculate(
    query: String,
    resultUnit: String
): Double? {
    Log.d(TAG, "calculate: calculating progress calculating $query to $resultUnit")
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
        var result = 0.0
        val valueList = ArrayList<Double>()
        val unitList = ArrayList<String>()
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
            Log.d(TAG, "calculate: calculating progress $it")
        }
        unitList.forEach {
            Log.d(TAG, "calculate: calculating progress $it")
        }
        if (valueList.size != unitList.size) return null
        for (index in 0 until valueList.size) {
            val k = valueList[index] * multiply(unitList[index])
            result += k
            Log.d(TAG, "calculate: calculating progress $k")
            Log.d(TAG, "calculate: calculating progress $result")
        }
        return getResult(result, resultUnit)
    } catch (e: java.lang.Exception) {
        Log.e(TAG, "calculate: calculating progress $e")
        Log.e(TAG, "calculate: calculating progress ${e.message}")
        Log.e(TAG, "calculate: calculating progress ${e.printStackTrace()}")
        Log.e(TAG, "calculate: calculating progress ${e.stackTrace}")
        return null
    }
}

fun getResult(result: Double, resultUnit: String): Double {
    return when (resultUnit) {
        BIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $BIT_NAME")
            result
        }
        KILOBIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $KILOBIT_NAME")
            result / 1e3
        }
        MEGABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $MEGABIT_NAME")
            result / 1e6
        }
        GIGABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $GIGABIT_NAME")
            result / 1e9
        }
        TERABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $TERABIT_NAME")
            result / 1e12
        }
        PETABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $PETABIT_NAME")
            result / 1e15
        }
        BYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $BYTE_NAME")
            result / 8
        }
        KILOBYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $KILOBYTE_NAME")
            result / (8e3)
        }
        MEGABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $MEGABYTE_NAME")
            result / (8e6)
        }
        GIGABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $GIGABYTE_NAME")
            result / (8e9)
        }
        TERABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $TERABYTE_NAME")
            result / (8e12)
        }
        PETABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to $PETABYTE_NAME")
            result / (8e15)
        }

        else -> {
            Log.d(TAG, "multiply: calculating progress $BIT_NAME to unknown")
            0.0
        }
    }
}

fun multiply(acronym: String): Double {
    return when (acronym) {
        BIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 1.0")
            1.0
        }
        KILOBIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 8.0")
            1e3
        }
        MEGABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 1000.0")
            1e6
        }
        GIGABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 8000000.0")
            1e9
        }
        TERABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 8000000000.0")
            1e12
        }
        PETABIT_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 8000000000000.0")
            1e15
        }
        BYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 8e15")
            8.0
        }
        KILOBYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 8e15")
            8e3
        }
        MEGABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 8e15")
            8e6
        }
        GIGABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 8e15")
            8e9
        }
        TERABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 8e15")
            8e12
        }
        PETABYTE_ACRONYM -> {
            Log.d(TAG, "multiply: calculating progress returned 8e15")
            8e15
        }

        else -> {
            Log.d(TAG, "multiply: calculating progress returned 0.0")
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
