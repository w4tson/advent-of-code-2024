package com.aoc.day02

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day02Test {

    val input = readInput("/day02/day02.txt")
    val testInput = readInput("/day02/day02-test.txt")

    val testStr = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(2, part1(testStr))
    }

    @Test
    fun part1() {
        print(part1(input))
    }

    @Test
    fun name() {
        assertEquals(4, part2(testStr))
    }

    @Test
    fun part2() {
        print(part2(input))
    }
}

