package com.merricklabs.adventofcode2019.day7

import com.merricklabs.adventofcode2019.day5.IntcodeExecutor
import org.paukov.combinatorics3.Generator

object AmplifierExecutor {

    fun generatePhasePermutations(start: Int, end: Int): List<List<Int>> {
        // Phase settings are in the range [0,4]
        return Generator.permutation((start..end).toList())
                .simple()
                .toList()
    }

    fun execAmplifiers(program: MutableList<Int>,
                       phaseSettings: List<Int>,
                       initialSignal: Int = 0): Int? {
        var signal = initialSignal
        val numAmplifiers = 0..4

        for (i in numAmplifiers) {
            val input = listOf(phaseSettings[i], signal)
            val executor = IntcodeExecutor(program, input)
            val result = executor.execute()
            signal = if (result.isNotEmpty()) {
                result.toInt()
            } else {
                return null
            }
        }

        return signal
    }

    /**
     * Exec in the part 2 feedback loop arrangement.
     * Throw an exception if an infinite loop is detected.
     */
    fun execWithFeedback(program: MutableList<Int>,
                         phaseSettings: List<Int>,
                         initialSignal: Int = 0): List<Int> {

        var outputs = mutableListOf<Int?>()
        var result: Int? = initialSignal
        while (result != null) {
            val previousResult = result
            result = execAmplifiers(program, phaseSettings, result)
            if (previousResult == result) {
                throw RuntimeException("Infinite loop detected. Halting.")
            }
            outputs.add(result)
        }

        return outputs.filterNotNull()
    }
}