package com.merricklabs.adventofcode2019.day1

import io.kotlintest.shouldBe
import org.testng.annotations.Test

object RocketModuleTest {

    @Test
    fun `Test from example of mass 12`(){
        val expectedResult = 2
        RocketModule(12).getFuel() shouldBe expectedResult
    }

    @Test
    fun `Test from example of mass 14`(){
        val expectedResult = 2
        RocketModule(14).getFuel() shouldBe expectedResult
    }

    @Test
    fun `Test from example of mass 1969`(){
        val expectedResult = 654
        RocketModule(1969).getFuel() shouldBe expectedResult
    }

    @Test
    fun `Test from example of mass 100756`(){
        val expectedResult = 33583
        RocketModule(100756).getFuel() shouldBe expectedResult
    }
}
