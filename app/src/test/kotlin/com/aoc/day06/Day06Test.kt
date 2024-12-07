package com.aoc.day06

import Compass.*
import Coord
import Util.Companion.readInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day06Test {

    val input = readInput("/day06/day06.txt")
    val testInput = readInput("/day06/day06-test.txt")

    val testStr = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent()


    val testStr1 = """
         ..#.
         ...#
         ..^.
    """.trimIndent()

    val testStr2 = """
        .#.
        #.#
        #^.
        ...
    """.trimIndent()
    val testStr3 = """
        .#.
        ..#
        #.<
        .#.
    """.trimIndent()

    fun String.toLabMap() : LabMap {
        val map = mutableMapOf<Coord, Char>()
        var guard = Guard(Coord.origin, NORTH)
        this.lines().forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char != '.' && char != '#') {

                    val direction = when (char) {
                        '^' -> NORTH
                        '>' -> EAST
                        'v' -> SOUTH
                        '<' -> WEST
                        else -> throw AssertionError("Unexpected char $char")
                    }
                    guard = Guard(Coord(x, y), direction)
                } else if (char == '#' ){
                    map.put(Coord(x, y), char)
                }
            }
        }

        return LabMap(map.keys.toList(), this.lines()[0].length, this.lines().size, guard)
    }

    @Test
    fun test1() {
        assertEquals(41, testStr.toLabMap().part1())
    }

    @Test
    fun part1() {
        input.toLabMap().part1().also { println(it) }
    }

    @Test
    fun part2() {
        val labMap = testStr.toLabMap()
        labMap.part1()
        val expected = listOf(Coord(3,6),Coord(6,7),Coord(7,7),Coord(1,8),Coord(3,8),Coord(7,9))
        assertEquals(expected.toSet(), labMap.newObstacles)

    }

    @Test
    fun part22() {
        val labMap = input.toLabMap()
        labMap.part1()
        labMap.newObstacles.size.also { println(it) }
    }

    @Test
    fun test2() {
        assertEquals(0, testStr1.toLabMap().part2())
        assertEquals(2, testStr2.toLabMap().part2())
    }
}

