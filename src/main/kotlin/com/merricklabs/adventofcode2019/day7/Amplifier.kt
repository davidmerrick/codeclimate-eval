package com.merricklabs.adventofcode2019.day7

import com.merricklabs.adventofcode2019.day5.ExecutionState.NOT_STARTED
import com.merricklabs.adventofcode2019.day5.IntcodeExecutor
import com.merricklabs.adventofcode2019.day5.OpCode.HALT
import com.merricklabs.adventofcode2019.day7.AmpState.HALTED
import com.merricklabs.adventofcode2019.day7.AmpState.RUNNING

class Amplifier(private val phaseSetting: Int,
                program: List<Int>) : IntcodeExecutor(program) {

    private var _state = RUNNING
    private var _lastOutput: Int? = null

    fun executeWithSignal(signal: Int?): Int? {
        val input = if (this.state == NOT_STARTED) {
            phaseSetting
        } else {
            signal
        }
        val result = this.execute(input)
        if (this.lastInstruction?.opCode == HALT) {
            _state = HALTED
        } else {
            _lastOutput = result
        }
        return result
    }
}