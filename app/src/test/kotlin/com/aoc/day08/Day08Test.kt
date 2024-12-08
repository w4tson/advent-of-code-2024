package com.aoc.day08

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day08Test {

    val input = readInput("/day08/day08.txt")
    val testInput = readInput("/day08/day08-test.txt")

    val testStr = """
        ...
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(14, testInput.toCity().part1())
    }

    @Test
    fun test2() {
        assertEquals(34, testInput.toCity().part1())
    }

    @Test
    fun part1() {
        input.toCity().part1().also { println(it) }
    }

    @Test
    fun part2() {
        input.toCity().part2().also { println(it) }
    }
}

