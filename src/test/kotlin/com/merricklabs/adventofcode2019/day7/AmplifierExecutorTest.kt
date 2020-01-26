package com.merricklabs.adventofcode2019.day7

import com.merricklabs.adventofcode2019.day7.AmplifierExecutor.Companion.generatePhasePermutations
import com.merricklabs.adventofcode2019.testutil.toIntCodeProgram
import io.kotlintest.shouldBe
import org.testng.annotations.Test

class AmplifierExecutorTest {

    @Test
    fun `Max output test 3`() {
        val program = mutableListOf(3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33,
                1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0)
        val combos = generatePhasePermutations(0, 4)

        val output = combos
                .mapNotNull { AmplifierExecutor(program, it).execAmplifiers() }
                .max()

        output shouldBe 65210
    }

    @Test
    fun `Test with my input`() {
        val program = this::class.java.getResourceAsStream("input.txt")
                .toIntCodeProgram()

        val combinations = generatePhasePermutations(0, 4)

        val max = combinations
                .mapNotNull { AmplifierExecutor(program, it).execAmplifiers() }
                .max()
        println(max)
    }

    @Test
    fun `Debugging part 2`() {
        val program = listOf(3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26,
                27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5)
        val amplifier = Amplifier(9, program)

        var result = 0
        result = amplifier.step(result)!!
        result shouldBe 5

        result = amplifier.step(result)!!
        result shouldBe 15
    }

    @Test
    fun `Part 2 test 1`() {
        val program = listOf(3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26,
                27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5)
        val combos = listOf(9, 8, 7, 6, 5)
        val executor = AmplifierExecutor(program, combos)

        val output = executor.execWithFeedback()

        output shouldBe 139629729
    }

    @Test
    fun `Max from part 2 test 1`() {
        val program = listOf(3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26,
                27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5)
        val combos = generatePhasePermutations(5, 9)
        val max = combos
                .mapNotNull { AmplifierExecutor(program, it).execWithFeedback() }
                .max()

        max shouldBe 139629729
    }

    @Test
    fun `Part 2 my input`() {
        val program = this::class.java.getResourceAsStream("input.txt")
                .toIntCodeProgram()

        val combinations = generatePhasePermutations(5, 9)

        val max = combinations
                .mapNotNull { AmplifierExecutor(program, it).execWithFeedback() }
                .max()
        println(max)
    }
}