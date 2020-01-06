package com.merricklabs.adventofcode2019.day8

import io.kotlintest.matchers.collections.shouldContainInOrder
import io.kotlintest.shouldBe
import org.testng.annotations.Test

class SpaceImageTest {

    @Test
    fun `Get layers test`() {
        val digits = "123456789012"
        val image = SpaceImage(digits, 3, 2)
        val layers = image.getLayers()
        layers.size shouldBe 2
        layers[0].shouldContainInOrder(1, 2, 3, 4, 5, 6)
        layers[1].shouldContainInOrder(7, 8, 9, 0, 1, 2)
    }

    @Test
    fun `Test with my input`() {
        val digits = this::class.java.getResourceAsStream("input.txt")
                .bufferedReader()
                .readText()
        val image = SpaceImage(digits, 25, 6)
        val layers = image.getLayers()
        val layer = layers.minBy { layer -> layer.count { it == 0 } }!!
        println(layer.count { it == 1 } * layer.count { it == 2 })
    }
}