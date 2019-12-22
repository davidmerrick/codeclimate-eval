package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.ADD
import com.merricklabs.adventofcode2019.day5.OpCode.EQUALS
import com.merricklabs.adventofcode2019.day5.OpCode.INPUT
import com.merricklabs.adventofcode2019.day5.OpCode.JUMP_IF_FALSE
import com.merricklabs.adventofcode2019.day5.OpCode.JUMP_IF_TRUE
import com.merricklabs.adventofcode2019.day5.OpCode.LESS_THAN
import com.merricklabs.adventofcode2019.day5.OpCode.MULT
import com.merricklabs.adventofcode2019.day5.OpCode.OUTPUT

data class Instruction(
        val header: InstructionHeader,
        val params: List<Int>,
        val input: Int?,
        val program: MutableList<Int>
) {
    fun execute(): ExecutionResult {
        return when (header.opCode) {
            MULT -> multiply()
            ADD -> add()
            INPUT -> input()
            OUTPUT -> output()
            JUMP_IF_TRUE -> jumpIfTrue()
            JUMP_IF_FALSE -> jumpIfFalse()
            LESS_THAN -> lessThan()
            EQUALS -> equals()
            else -> throw UnsupportedOperationException("Opcode ${header.opCode} not supported.")
        }
    }

    private fun jumpIfTrue(): ExecutionResult {
        val firstParam = getParam(0, program)
        return if (firstParam != 0) {
            val secondParam = getParam(1, program)
            ExecutionResult(jumpAddr = secondParam)
        } else {
            ExecutionResult()
        }
    }

    private fun jumpIfFalse(): ExecutionResult {
        val firstParam = getParam(0, program)
        return if (firstParam == 0) {
            val secondParam = getParam(1, program)
            ExecutionResult(jumpAddr = secondParam)
        } else {
            ExecutionResult()
        }
    }

    private fun lessThan(): ExecutionResult {
        val firstParam = getParam(0, program)
        val secondParam = getParam(1, program)
        val thirdParam = params[2]
        program[thirdParam] = if (firstParam < secondParam) {
            1
        } else {
            0
        }
        return ExecutionResult()
    }

    private fun equals(): ExecutionResult {
        val firstParam = getParam(0, program)
        val secondParam = getParam(1, program)
        val thirdParam = params[2]
        program[thirdParam] = if (firstParam == secondParam) {
            1
        } else {
            0
        }
        return ExecutionResult()
    }

    private fun multiply(): ExecutionResult {
        val firstParam = getParam(0, program)
        val secondParam = getParam(1, program)

        // Parameters that an instruction writes to
        // will never be in immediate mode.
        val thirdParam = params[2]
        program[thirdParam] = firstParam * secondParam
        return ExecutionResult()
    }

    private fun input(): ExecutionResult {
        program[params[0]] = input!!
        return ExecutionResult()
    }

    private fun output(): ExecutionResult {
        val output = program[params[0]].toString()
        return ExecutionResult(output = output)
    }

    private fun add(): ExecutionResult {
        val firstParam = getParam(0, program)
        val secondParam = getParam(1, program)
        val thirdParam = params[2]

        // Parameters that an instruction writes to
        // will never be in immediate mode.
        program[thirdParam] = firstParam + secondParam
        return ExecutionResult()
    }

    private fun getParam(index: Int, program: MutableList<Int>) = if (header.paramModes[index] == 0) {
        program[params[index]]
    } else {
        params[index]
    }
}