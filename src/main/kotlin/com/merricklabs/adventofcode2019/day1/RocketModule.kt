package com.merricklabs.adventofcode2019.day1

import kotlin.math.floor
import kotlin.math.roundToInt

data class RocketModule(val mass: Int) {
    fun getFuel(): Int = (floor((mass/3).toDouble()) - 2).roundToInt()
}