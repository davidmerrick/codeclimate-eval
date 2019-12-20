package com.merricklabs.adventofcode2019.day5

enum class OpCode(val value: Int) {
    ADD(1),
    INPUT(3),
    OUTPUT(4),
    MULT(2),
    HALT(99);

    companion object {
        private val map = OpCode.values().associateBy(OpCode::value)
        fun fromInt(type: Int) = map[type]
    }
}

