package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.ExecutionState.AWAITING_INPUT
import com.merricklabs.adventofcode2019.day5.ExecutionState.HALTED
import com.merricklabs.adventofcode2019.day5.ExecutionState.NOT_STARTED
import com.merricklabs.adventofcode2019.day5.ExecutionState.PAUSED
import com.merricklabs.adventofcode2019.day5.ExecutionState.RUNNING
import com.merricklabs.adventofcode2019.day5.OpCode.HALT
import com.merricklabs.adventofcode2019.day5.OpCode.INPUT

open class IntcodeExecutor(program: List<Int>) {
    // Defensively make a copy
    private val _program = program.toMutableList()
    private var _instructionPointer = 0
    private var _lastInput: Int? = null
    private var _lastOutput: Int? = null
    val lastOutput
        get() = _lastOutput

    private var _lastInstruction: InstructionHeader? = null
    private var _state = NOT_STARTED
    val state
        get() = _state

    val lastInstruction
        get() = _lastInstruction

    // Todo: Might need to rework this to be a state machine so it can accept input
    fun execute(input: Int?): Int? {
        _state = RUNNING
        _lastInput = input

        while (_instructionPointer < _program.size) {

            // Read
            val header = InstructionHeader(_program[_instructionPointer])
            _lastInstruction = header
            if (header.opCode == HALT) {
                _state = HALTED
                break
            }

            // Todo: Current bug is that it always takes the phase setting as the input
            // It should be taking the signal.
            if (header.opCode == INPUT) {
                _state = AWAITING_INPUT
                break
            }

            val params = _program.subList(_instructionPointer + 1, _instructionPointer + 1 + header.numParams)
            val instruction = Instruction(header, params, input, _program)

            // Compute/store. Might want to break these out.
            val result = instruction.execute()
            _instructionPointer = result.jumpAddr?.let {
                it
            } ?: _instructionPointer + header.numParams + 1

            // Output
            result.output?.let {
                _state = PAUSED
                _lastOutput = it
                return _lastOutput
            }
        }

        return null
    }
}