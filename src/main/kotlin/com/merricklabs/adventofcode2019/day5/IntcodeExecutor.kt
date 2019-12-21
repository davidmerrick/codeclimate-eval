package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.HALT

class IntcodeExecutor(val program: MutableList<Int>, val input: Int?) {

    private var instructionPointer = 0

    fun execute() {
        while (instructionPointer < program.size) {
            val header = InstructionHeader(program[instructionPointer])
            val params = program.subList(instructionPointer + 1, instructionPointer + 1 + header.numParams)
            val instruction = Instruction(header, params, input)

            // Todo: Could have execution result that
            // executor could use to set the instruction pointer
            // Result could be optional. Default behavior is to
            // advance to header.numParams + 1

            instruction.execute(program)

            if (header.opCode == HALT) {
                return
            }
            instructionPointer += header.numParams + 1
        }
    }
}