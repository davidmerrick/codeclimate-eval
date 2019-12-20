package com.merricklabs.adventofcode2019.day5

import com.merricklabs.adventofcode2019.day5.OpCode.*
import io.kotlintest.shouldBe
import org.testng.annotations.Test

class InstructionHeaderTest {

    @Test
    fun `Parse example`(){
        val header = InstructionHeader(1002)
        header.opCode shouldBe MULT
        header.numParams shouldBe 2
        header.paramModes.size shouldBe 2
        header.paramModes[0] shouldBe 1
        header.paramModes[1] shouldBe 0
    }
}