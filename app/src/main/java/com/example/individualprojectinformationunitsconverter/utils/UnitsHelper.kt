package com.example.individualprojectinformationunitsconverter.utils

import android.content.Context
import com.example.individualprojectinformationunitsconverter.R
import com.example.individualprojectinformationunitsconverter.models.MyUnit

fun getUnitList(): ArrayList<MyUnit> {
    val list = ArrayList<MyUnit>()
    /**
     * bits
     */
    list.add(
        MyUnit(
            unitName = BIT_NAME,
            unitAcronym = BIT_ACRONYM
        )
    )
    list.add(
        MyUnit(
            unitName = KILOBIT_NAME,
            unitAcronym = KILOBIT_ACRONYM
        )
    )
    list.add(
        MyUnit(
            unitName = MEGABIT_NAME,
            unitAcronym = MEGABIT_ACRONYM
        )
    )
    list.add(
        MyUnit(
            unitName = GIGABIT_NAME,
            unitAcronym = GIGABIT_ACRONYM
        )
    )
    list.add(
        MyUnit(
            unitName = TERABIT_NAME,
            unitAcronym = TERABIT_ACRONYM
        )
    )
    list.add(
        MyUnit(
            unitName = PETABIT_NAME,
            unitAcronym = PETABIT_ACRONYM
        )
    )

    /**
     * bytes
     */
    list.add(
        MyUnit(
            unitName = BYTE_NAME,
            unitAcronym = BYTE_ACRONYM
        )
    )
    list.add(
        MyUnit(
            unitName = KILOBYTE_NAME,
            unitAcronym = KILOBYTE_ACRONYM
        )
    )
    list.add(
        MyUnit(
            unitName = MEGABYTE_NAME,
            unitAcronym = MEGABYTE_ACRONYM
        )
    )
    list.add(
        MyUnit(
            unitName = GIGABYTE_NAME,
            unitAcronym = GIGABYTE_ACRONYM
        )
    )
    list.add(
        MyUnit(
            unitName = TERABYTE_NAME,
            unitAcronym = TERABYTE_ACRONYM
        )
    )
    list.add(
        MyUnit(
            unitName = PETABYTE_NAME,
            unitAcronym = PETABYTE_ACRONYM
        )
    )

    return list
}