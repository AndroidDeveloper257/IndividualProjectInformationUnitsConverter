package com.example.individualprojectinformationunitsconverter.utils

import android.content.Context
import com.example.individualprojectinformationunitsconverter.R
import com.example.individualprojectinformationunitsconverter.models.MyUnit

fun getUnitList(context: Context): ArrayList<MyUnit> {
    val list = ArrayList<MyUnit>()
    list.add(
        MyUnit(
            context.getString(R.string.bit_acronym),
            context.getString(R.string.bit_name)
        )
    )
    list.add(
        MyUnit(
            context.getString(R.string.byte_acronym),
            context.getString(R.string.byte_name)
        )
    )
    list.add(
        MyUnit(
            context.getString(R.string.kilobyte_acronym),
            context.getString(R.string.kilobyte_name)
        )
    )
    list.add(
        MyUnit(
            context.getString(R.string.megabyte_acronym),
            context.getString(R.string.megabyte_name)
        )
    )
    list.add(
        MyUnit(
            context.getString(R.string.gigabyte_acronym),
            context.getString(R.string.gigabyte_name)
        )
    )
    list.add(
        MyUnit(
            context.getString(R.string.terabyte_acronym),
            context.getString(R.string.terabyte_name)
        )
    )
    list.add(
        MyUnit(
            context.getString(R.string.petabyte_acronym),
            context.getString(R.string.petabyte_name)
        )
    )
    list.add(
        MyUnit(
            context.getString(R.string.exabyte_acronym),
            context.getString(R.string.exabyte_name)
        )
    )
    list.add(
        MyUnit(
            context.getString(R.string.zettabyte_acronym),
            context.getString(R.string.zettabyte_name)
        )
    )
    list.add(
        MyUnit(
            context.getString(R.string.yottabyte_acronym),
            context.getString(R.string.yottabyte_name)
        )
    )
    return list
}