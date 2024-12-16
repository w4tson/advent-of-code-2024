package com.aoc.day14

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day14Test {

    val input = readInput("/day14/day14.txt")
    val testInput = readInput("/day14/day14-test.txt")

    val testStr = """
        ...
    """.trimIndent()

    @Test
    fun test1() {
        part1(testStr)
    }

}

