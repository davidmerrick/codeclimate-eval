package com.merricklabs.adventofcode2019.day5

import org.testng.annotations.Test

class IntcodeExecutorTest {

    @Test
    fun `Print out program`(){
        val program = mutableListOf(1002, 4, 3, 4, 33)
        val executor = IntcodeExecutor(program)
        executor.execute()
        println(program)
    }

    @Test
    fun `Run test input`(){
        val program = this::class.java.getResourceAsStream("input.txt")
                .bufferedReader()
                .readText()
                .split(",")
                .map { it.toInt() }
                .toMutableList()
        val executor = IntcodeExecutor(program)
        executor.execute()
        println(program)
    }
}