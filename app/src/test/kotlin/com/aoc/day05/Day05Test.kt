package com.aoc.day05

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day05Test {

    val input = readInput("/day05/day05.txt")
    val testInput = readInput("/day05/day05-test.txt")

    val testStr = """
        ...
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(143, part1(testInput))

    }

    @Test
    fun part1() {
        println(part1(input))
    }

    @Test
    fun part2Test() {
        println(input.toPaperSorter().solvePart2())
    }

}

