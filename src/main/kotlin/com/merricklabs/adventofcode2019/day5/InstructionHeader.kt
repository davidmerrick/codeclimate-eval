package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.ADD
import com.merricklabs.adventofcode2019.day5.OpCode.HALT
import com.merricklabs.adventofcode2019.day5.OpCode.INPUT
import com.merricklabs.adventofcode2019.day5.OpCode.MULT
import com.merricklabs.adventofcode2019.day5.OpCode.OUTPUT

data class InstructionHeader(private val value: Int) {
    val opCode = OpCode.fromInt(value % 1000)!!
    val numParams: Int
    val paramModes: List<Int>

    init {
        numParams = when (opCode) {
            MULT, ADD -> 3
            INPUT, OUTPUT -> 2
            HALT -> 0
        }
        paramModes = if (value > 100) {
            value.toString()
                    .split("")
                    .filter { it != "" }
                    .map { it.toInt() }
                    .subList(0, 2)
                    .reversed()
        } else {
            // Default to position mode
            listOf(0, 0)
        }
    }
}