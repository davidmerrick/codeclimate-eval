package com.merricklabs.adventofcode2019.day7

import com.merricklabs.adventofcode2019.day5.IntcodeExecutor
import com.merricklabs.adventofcode2019.day5.OpCode.HALT
import com.merricklabs.adventofcode2019.day7.AmpState.HALTED
import com.merricklabs.adventofcode2019.day7.AmpState.RUNNING

class Amplifier(private val phaseSetting: Int,
                program: MutableList<Int>
) {
    val state
        get() = _state
    val lastOutput
        get() = _lastOutput

    private var _state = RUNNING
    private var _lastOutput: Int? = null

    // Defensively copy the program and store it internally
    private val executor = IntcodeExecutor(program.toMutableList())

    fun execute(signal: Int?): Int? {
        val input = listOf(phaseSetting, signal ?: 0)
        val result = executor.execute(input)
        if (executor.lastInstruction?.opCode == HALT) {
            _state = HALTED
        } else {
            _lastOutput = result
        }
        return result
    }
}