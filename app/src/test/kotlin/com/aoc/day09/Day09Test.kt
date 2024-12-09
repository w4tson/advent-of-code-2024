package com.aoc.day09

import Util.Companion.readInput
import charList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day09Test {

    val input = readInput("/day09/day09.txt")
    val testInput = readInput("/day09/day09-test.txt")

    val testStr = "2333133121414131402".trimIndent()

    @Test
    fun test1() {

    }

    @Test
    fun experiment() {
        assertEquals(1928, Disk(testStr).solve())
    }

    @Test
    fun part1() {
        Disk(input).solve().also { println(it) }
    }

    @Test
    fun testLL() {
        val ll = LinkedList<Int>()
        ll.append(1).append(2).append(3).append(4).append(5)
        val x = ll.sliceLast(2) {
            it != 4
        }
        println(x)


    }
}

