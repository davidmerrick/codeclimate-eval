package com.merricklabs.adventofcode2019.day7

import com.merricklabs.adventofcode2019.day7.AmpState.RUNNING
import org.paukov.combinatorics3.Generator

private const val NUM_AMPLIFIERS = 5
private const val INITIAL_SIGNAL = 0

class AmplifierExecutor(private val program: MutableList<Int>,
                        private val phaseSettings: List<Int>) {

    private val amplifiers = initializeAmplifiers()

    private fun initializeAmplifiers(): List<Amplifier> {
        return (0 until NUM_AMPLIFIERS).map {
            Amplifier(phaseSettings[it], program)
        }
    }

    fun execAmplifiers(signal: Int? = INITIAL_SIGNAL): Int? {
        var result = signal
        for (amplifier in amplifiers) {
            if (amplifier.state == RUNNING) {
                result = (amplifier.executeWithSignal(result))
            }
        }

        return result
    }

    /**
     * Exec in the part 2 feedback loop arrangement.
     * Throw an exception if an infinite loop is detected.
     */
    fun execWithFeedback(): Int? {
        var result: Int? = 0
        while (amplifiers.any { it.state == RUNNING }) {
            result = execAmplifiers(result)

        }

        return amplifiers[NUM_AMPLIFIERS - 1].lastOutput
    }

    companion object {
        fun generatePhasePermutations(start: Int, end: Int): List<List<Int>> {
            return Generator.permutation((start..end).toList())
                    .simple()
                    .toList()
        }
    }
}