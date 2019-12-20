package com.merricklabs.adventofcode2019.day5

import org.testng.annotations.Test

class IntcodeExecutorTest {


    @Test
    fun `Print out program`(){
        val executor = IntcodeExecutor(mutableListOf(1002, 4, 3, 4, 33))
        executor.execute()
    }
}