package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.HALT
import com.merricklabs.adventofcode2019.day5.OpCode.INPUT

class IntcodeExecutor(private val program: MutableList<Int>) {
    private var instructionPointer = 0

    fun execute(input: List<Int> = emptyList()): Int? {
        var inputPointer = 0

        while (instructionPointer < program.size) {
            val header = InstructionHeader(program[instructionPointer])
            if (header.opCode == HALT) {
                break
            }

            val currentInput = if (header.opCode == INPUT) {
                input[inputPointer++]
            } else null

            val params = program.subList(instructionPointer + 1, instructionPointer + 1 + header.numParams)
            val instruction = Instruction(header, params, currentInput, program)

            val result = instruction.execute()
            result.output?.let { return it }

            instructionPointer = result.jumpAddr?.let {
                it
            } ?: instructionPointer + header.numParams + 1
        }

        return null
    }
}