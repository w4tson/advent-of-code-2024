package com.aoc.day01

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day01Test {

    val input = readInput("/day01/day01.txt")
    val testInput = readInput("/day01/day01-test.txt")

    val testStr = """
        ...
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(11, part1(testInput))
        assertEquals(31, part2(testInput))
    }

    @Test
    fun part1() {
        println(part1(input))
    }

    @Test
    fun part2() {
        println(part2(input))
    }
}

