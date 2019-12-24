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
}