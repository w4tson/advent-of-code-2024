package com.aoc.day15

import Coord
import Util.Companion.readInput
import charList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


fun String.toWharehouse() : Wharehouse {
    val map = mutableMapOf<Coord, Char>()
    val (mapStr, instructions) = this.split("\n\n")
    mapStr.lines().forEachIndexed { y, line ->
        line.forEachIndexed { x, ch ->
            map.put(Coord(x, y), ch)

        }
    }
    return Wharehouse(map, mapStr.lines()[0].length, mapStr.lines().size, instructions.filter { it != '\n' }.charList())
}

fun String.toWharehousePart2() : Wharehouse {
    val map = mutableMapOf<Coord, Char>()
    val (mapStr, instructions) = this.split("\n\n")
    mapStr.lines().forEachIndexed { y, line ->
        (0 until (line.length*2) step 2).forEach { x ->
            val ch = line[x / 2]
            val (left, right) = when(ch) {
                'O' -> Pair('[',']')
                '@' -> Pair('@','.')
                else -> Pair(ch,ch)
            }

            map.put(Coord(x, y), left)
            map.put(Coord(x+1, y), right)

        }
    }
//    map.put(Coord(2,4), '.')
//    map.put(Coord(3,4), '[')
//    map.put(Coord(4,4), ']')
    return Wharehouse(map, mapStr.lines()[0].length * 2, mapStr.lines().size, instructions.filter { it != '\n' }.charList())
}



class Day15Test {

    val input = readInput("/day15/day15.txt")
    val testInput = readInput("/day15/day15-test.txt")
    val testInput2 = readInput("/day15/day15-test2.txt")
    val testInput3 = readInput("/day15/day15-test3.txt")
    val testInput4 = readInput("/day15/day15-test4.txt")
    val testInput5 = readInput("/day15/day15-test5.txt")
    val testInput6 = readInput("/day15/day15-test6.txt")

    val testStr = """
        ...
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(2028, testInput.toWharehouse().gpsOfAllBoxes())
    }

    @Test
    fun name() {
        input.toWharehouse().gpsOfAllBoxes().also { println(it) }
    }

    @Test
    fun part2test() {
        val wharehouse = input.toWharehousePart2()
//        wharehouse.printMap()
        wharehouse.gpsOfAllBoxes().also { println(it) }
    }

    @Test
    fun part2test6() {
        val wharehouse = testInput6.toWharehousePart2()
        wharehouse.printMap()
        wharehouse.gpsOfAllBoxes().also { println(it) }
    }
}
