package com.merricklabs.adventofcode2019.day5

import io.kotlintest.shouldBe
import org.testng.annotations.Test

class IntcodeExecutorTest {

    @Test
    fun `Print out program`(){
        val program = mutableListOf(1002, 4, 3, 4, 33)
        val executor = IntcodeExecutor(program, null)
        executor.execute()
        program[4] shouldBe 99
    }

    @Test
    fun `Test input`(){
        val program = mutableListOf(1101,100,-1,4,0)
        val executor = IntcodeExecutor(program, null)
        executor.execute()
        program[4] shouldBe 99
    }

    @Test(description = "This should output whatever the input is")
    fun `Test input 2`(){
        val program = mutableListOf(3,0,4,0,99)
        val executor = IntcodeExecutor(program, 5)
        executor.execute()
    }

    @Test
    fun `Run test input`(){
        val program = this::class.java.getResourceAsStream("input.txt")
                .bufferedReader()
                .readText()
                .split(",")
                .map { it.toInt() }
                .toMutableList()
        val executor = IntcodeExecutor(program, 1)
        executor.execute()
    }
}