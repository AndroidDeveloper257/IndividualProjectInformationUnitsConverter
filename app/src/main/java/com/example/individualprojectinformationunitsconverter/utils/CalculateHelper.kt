package com.example.individualprojectinformationunitsconverter.utils

import android.content.Context
import android.util.Log
import com.example.individualprojectinformationunitsconverter.R

fun calculate(
    query: String,
    resultUnit: String,
    context: Context
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
        var result: Double? = 0.0
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
            result?.plus(valueList[index] * multiply(unitList[index], context))
        }
        return result
    } catch (e: java.lang.Exception) {
        Log.e(TAG, "calculate: $e")
        Log.e(TAG, "calculate: ${e.message}")
        Log.e(TAG, "calculate: ${e.printStackTrace()}")
        Log.e(TAG, "calculate: ${e.stackTrace}")
        return null
    }
}

fun multiply(acronym: String, context: Context): Double {
    return when (acronym) {
        context.getString(R.string.bit_acronym) -> {
            1.0
        }
        context.getString(R.string.byte_acronym) -> {
            8.0
        }
        context.getString(R.string.kilobyte_acronym) -> {
            1.0
        }
        context.getString(R.string.megabyte_acronym) -> {
            1.0
        }
        context.getString(R.string.gigabyte_acronym) -> {
            1.0
        }
        context.getString(R.string.terabyte_acronym) -> {
            1.0
        }
        context.getString(R.string.petabyte_acronym) -> {
            1.0
        }
        context.getString(R.string.exabyte_acronym) -> {
            1.0
        }
        context.getString(R.string.zettabyte_acronym) -> {
            1.0
        }
        context.getString(R.string.yottabyte_acronym) -> {
            1.0
        }
        else -> {
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
