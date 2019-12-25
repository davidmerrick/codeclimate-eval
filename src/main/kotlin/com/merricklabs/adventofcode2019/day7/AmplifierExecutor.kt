package com.merricklabs.adventofcode2019.day7

import com.merricklabs.adventofcode2019.day5.IntcodeExecutor
import org.paukov.combinatorics3.Generator

private const val NUM_AMPLIFIERS = 5
private const val INITIAL_SIGNAL = 0

class AmplifierExecutor(private val program: MutableList<Int>) {

    private val amplifiers = initializeAmplifiers()

    private fun initializeAmplifiers(): List<IntcodeExecutor> {
        return (0 until NUM_AMPLIFIERS).map {
            IntcodeExecutor(program)
        }
    }

    fun execAmplifiers(phaseSettings: List<Int>,
                       initialSignal: Int = INITIAL_SIGNAL): Int? {
        var signal = initialSignal

        for ((i, amplifier) in amplifiers.withIndex()) {
            val input = listOf(phaseSettings[i], signal)
            signal = (amplifier.execute(input) ?: return null)
        }

        return signal
    }

    /**
     * Exec in the part 2 feedback loop arrangement.
     * Throw an exception if an infinite loop is detected.
     */
    fun execWithFeedback(phaseSettings: List<Int>): Int? {
        var previousResult: Int? = null
        var result: Int? = 0
        while (result != null) {
            val previousResult = result
            result = execAmplifiers(phaseSettings, result)
            if (previousResult == result) {
                throw RuntimeException("Infinite loop detected. Halting.")
            }
        }

        return previousResult
    }

    companion object {
        fun generatePhasePermutations(start: Int, end: Int): List<List<Int>> {
            return Generator.permutation((start..end).toList())
                    .simple()
                    .toList()
        }
    }
}