package com.aoc.day13

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day13Test {

    val input = readInput("/day13/day13.txt")
    val testInput = readInput("/day13/day13-test.txt")

    val testStr = """
        ...
    """.trimIndent()

    @Test
    fun test1() {
        part1(testStr)
    }

}

