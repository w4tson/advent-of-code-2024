package com.aoc.day03

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day03Test {

    val input = readInput("/day03/day03.txt")
    val testInput = readInput("/day03/day03-test.txt")

    val testStr = """
        ...
    """.trimIndent()

    @Test
    fun test1() {
        println(part1(testInput))
    }

    @Test
    fun part1() {
        println(part1(input))
    }

    @Test
    fun part2Test() {
        println(part2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"))
    }

    @Test
    fun part2() {
        println(part2(input))
    }

}

