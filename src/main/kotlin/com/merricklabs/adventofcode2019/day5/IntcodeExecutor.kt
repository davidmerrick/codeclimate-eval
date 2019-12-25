package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.HALT
import com.merricklabs.adventofcode2019.day5.OpCode.INPUT

class IntcodeExecutor(private val program: MutableList<Int>) {
    val instructionPointer: Int
        get() = _instructionPointer

    private var _instructionPointer = 0

    fun execute(input: List<Int> = emptyList()): Int? {
        var inputPointer = 0

        while (_instructionPointer < program.size) {
            val header = InstructionHeader(program[_instructionPointer])
            if (header.opCode == HALT) {
                break
            }

            val currentInput = if (header.opCode == INPUT) {
                input[inputPointer++]
            } else null

            val params = program.subList(_instructionPointer + 1, _instructionPointer + 1 + header.numParams)
            val instruction = Instruction(header, params, currentInput, program)

            val result = instruction.execute()
            result.output?.let { return it }

            _instructionPointer = result.jumpAddr?.let {
                it
            } ?: _instructionPointer + header.numParams + 1
        }

        return null
    }
}