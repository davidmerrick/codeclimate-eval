package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.ADD
import com.merricklabs.adventofcode2019.day5.OpCode.INPUT
import com.merricklabs.adventofcode2019.day5.OpCode.MULT
import com.merricklabs.adventofcode2019.day5.OpCode.OUTPUT

data class Instruction(
        val header: InstructionHeader,
        val params: List<Int>,
        val input: Int?
) {
    fun execute(program: MutableList<Int>) {
        when (header.opCode) {
            MULT -> multiply(program)
            ADD -> add(program)
            INPUT -> input(program)
            OUTPUT -> output(program)
        }
    }

    private fun multiply(program: MutableList<Int>) {
        val firstParam = getParam(0, program)
        val secondParam = getParam(1, program)
        program[params[2]] = firstParam * secondParam
    }

    private fun input(program: MutableList<Int>) {
        program[params[0]] = input!!
    }

    private fun output(program: MutableList<Int>) {
        println(program[params[0]])
    }

    private fun add(program: MutableList<Int>) {
        val firstParam = getParam(0, program)
        val secondParam = getParam(1, program)
        program[params[2]] = firstParam + secondParam
    }

    private fun getParam(index: Int, program: MutableList<Int>) = if (header.paramModes[index] == 0) {
        program[params[index]]
    } else {
        params[index]
    }
}