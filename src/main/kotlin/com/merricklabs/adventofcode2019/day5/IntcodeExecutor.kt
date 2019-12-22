package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.HALT

class IntcodeExecutor(
        private val program: MutableList<Int>,
        private val input: Int
) {

    fun execute(): String {
        var instructionPointer = 0
        var output = ""

        while (instructionPointer < program.size) {
            val header = InstructionHeader(program[instructionPointer])
            if (header.opCode == HALT) {
                break
            }

            val params = program.subList(instructionPointer + 1, instructionPointer + 1 + header.numParams)
            val instruction = Instruction(header, params, input, program)

            val result = instruction.execute()
            output += result.output

            instructionPointer = result.jumpAddr?.let {
                it
            } ?: instructionPointer + header.numParams + 1
        }

        return output
    }
}