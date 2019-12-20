package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.ADD
import com.merricklabs.adventofcode2019.day5.OpCode.HALT
import com.merricklabs.adventofcode2019.day5.OpCode.INPUT
import com.merricklabs.adventofcode2019.day5.OpCode.MULT
import com.merricklabs.adventofcode2019.day5.OpCode.OUTPUT

private const val HEADER_LENGTH = 5

data class InstructionHeader(private val value: Int) {
    val opCode = OpCode.fromInt(value % 100)!!
    val numParams: Int
    val paramModes: List<Int>

    init {
        numParams = when (opCode) {
            MULT, ADD -> 3
            INPUT, OUTPUT -> 1
            HALT -> 0
        }
        paramModes = value.toString()
                .padStart(HEADER_LENGTH, '0')
                .split("")
                .filter { it != "" }
                .map { it.toInt() }
                .subList(0, 3)
                .reversed()
    }
}