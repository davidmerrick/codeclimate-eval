package com.merricklabs.adventofcode2019.day3

import io.kotlintest.shouldBe
import org.testng.annotations.Test
import kotlin.math.abs

object CoordinateParserTest {

    @Test
    fun `Initial example`() {
        val lineA = Line("R8,U5,L5,D3")
        val lineB = Line("U7,R6,D4,L4")

        val intersection = lineA.pointSet.intersect(lineB.pointSet)
        val closest = intersection.filter { it.first > 0 && it.second > 0 }
                .map { abs(it.first) + abs(it.second) }.min()!!

        closest shouldBe 6
    }

    @Test
    fun `Second example`() {
        val lineA = Line("R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val lineB = Line("U62,R66,U55,R34,D71,R55,D58,R83")

        val intersection = lineA.pointSet.intersect(lineB.pointSet)
        val closest = intersection.filter { it.first > 0 && it.second > 0 }
                .map { abs(it.first) + abs(it.second) }.min()!!

        closest shouldBe 159
    }

    @Test
    fun `Third example`() {
        val lineA = Line("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
        val lineB = Line("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

        val intersection = lineA.pointSet.intersect(lineB.pointSet)
        val closest = intersection.filter { it.first > 0 && it.second > 0 }
                .map { abs(it.first) + abs(it.second) }.min()!!

        closest shouldBe 135
    }

    @Test
    fun `Challenge input`() {
        val input = this::class.java.getResourceAsStream("input.txt")
                .bufferedReader()
                .readLines()

        val lineA = Line(input[0])
        val lineB = Line(input[1])

        val intersection = lineA.pointSet.intersect(lineB.pointSet)
        val closest = intersection
                .filter { it.first > 0 && it.second > 0 }
                .map { abs(it.first) + abs(it.second) }.min()!!

        closest shouldBe 896
    }

    @Test
    fun `Test distanceTo`(){
        // Todo: Confirm this function is okay

        val lineA = Line("R8,U5,L5,D3")
        lineA.distanceTo(Pair(4,0)) shouldBe 3
    }

    @Test
    fun `Challenge 2 input`() {
        val input = this::class.java.getResourceAsStream("input.txt")
                .bufferedReader()
                .readLines()

        val lineA = Line(input[0])
        val lineB = Line(input[1])

        val intersection = lineA.pointSet.intersect(lineB.pointSet)
        val shortest = intersection
                .filter { it.first > 0 && it.second > 0 }
                .map { lineA.distanceTo(it) + lineB.distanceTo(it) }
                .min()!!

        shortest shouldBe 151561
    }


}