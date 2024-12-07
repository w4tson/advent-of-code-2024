package com.aoc.day07

import Util.Companion.readInput
import com.google.common.collect.Lists.cartesianProduct
import combinations
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import permutations
import repeat


class Day07Test {

    val input = readInput("/day07/day07.txt")
    val testInput = readInput("/day07/day07-test.txt")

    val testStr = """
        ...
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(3749, part1(testInput.toEquations()))
    }

    @Test
    fun test2() {
        assertEquals(11387, part2(testInput.toEquations()))
    }

    @Test
    fun part1Actual() {
        part1(input.toEquations()).also { println(it) }
    }

    @Test
    fun part2Actual() {
        part2(input.toEquations()).also { println(it) }
    }

    @Test
    fun name() {
        assertTrue(Equation(292, values = listOf(11,6,16,20)).isTrue())
    }

    fun String.toEquations() : List<Equation> = this.lines().map {
        val (answerStr, valuesStr) = it.split(": ")
        Equation(answerStr.toLong(), valuesStr.split(" ").map { it.toLong() })
    }

}

