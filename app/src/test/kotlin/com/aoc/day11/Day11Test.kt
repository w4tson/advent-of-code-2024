package com.aoc.day11

import Util.Companion.readInput
import org.junit.jupiter.api.Test



class Day11Test {

    val input = readInput("/day11/day11.txt")
    val testInput = readInput("/day11/day11-test.txt")

    val testStr = """
         0 1 10 99 999
    """.trimIndent()


    @Test
    fun part1Test() {
        afterNBlinks(input, 25).also { println(it) }
    }

    @Test
    fun part2Test() {
        afterNBlinks(input, 75).also { println(it) }
    }
}

