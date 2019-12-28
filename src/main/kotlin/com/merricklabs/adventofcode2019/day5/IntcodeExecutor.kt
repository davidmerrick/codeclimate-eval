package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.HALT
import com.merricklabs.adventofcode2019.day5.OpCode.INPUT

class IntcodeExecutor(private val program: MutableList<Int>) {
    private var _instructionPointer = 0
    private var _lastInput: List<Int> = emptyList()
    private var _lastInstruction: InstructionHeader? = null

    val lastInstruction
        get() = _lastInstruction

    fun execute(input: List<Int> = emptyList()): Int? {
        _lastInput = input
        var inputPointer = 0

        while (_instructionPointer < program.size) {
            val header = InstructionHeader(program[_instructionPointer])
            _lastInstruction = header
            if (header.opCode == HALT) {
                break
            }

            val currentInput = if (header.opCode == INPUT) {
                input[inputPointer++]
            } else null

            val params = program.subList(_instructionPointer + 1, _instructionPointer + 1 + header.numParams)
            val instruction = Instruction(header, params, currentInput, program)

            val result = instruction.execute()
            _instructionPointer = result.jumpAddr?.let {
                it
            } ?: _instructionPointer + header.numParams + 1

            result.output?.let { return it }
        }

        return null
    }
}