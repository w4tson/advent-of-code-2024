package com.aoc.day04

import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day04Test {

    val input = readInput("/day04/day04.txt")
    val testInput = readInput("/day04/day04-test.txt")

    val testStr = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(18, testStr.toWordSearch().countXmas())
    }

    @Test
    fun part1() {
        println(input.toWordSearch().countXmas())
    }

    @Test
    fun part2() {
        println(input.toWordSearch().countMAS())
    }
}

