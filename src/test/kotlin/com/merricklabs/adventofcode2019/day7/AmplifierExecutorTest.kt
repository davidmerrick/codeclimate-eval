package com.merricklabs.adventofcode2019.day7

import com.merricklabs.adventofcode2019.day7.AmplifierExecutor.Companion.generatePhasePermutations
import com.merricklabs.adventofcode2019.testutil.toIntCodeProgram
import io.kotlintest.shouldBe
import org.testng.annotations.Test

class AmplifierExecutorTest {

    @Test
    fun `Part 2 my input`() {
        val program = this::class.java.getResourceAsStream("input.txt")
                .toIntCodeProgram()
        val foo = "bar"
        val combinations = generatePhasePermutations(5, 9)

        val max = combinations
                .mapNotNull { AmplifierExecutor(program, it).execWithFeedback() }
                .max()
        println(max)
    }
}