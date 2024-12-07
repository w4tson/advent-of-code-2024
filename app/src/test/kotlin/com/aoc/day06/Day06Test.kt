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
    fun part2Test() {
        val labMap = testStr.toLabMap()
        assertEquals(6, labMap.part2())

    }

    @Test
    fun part2() {
        input.toLabMap().part2().also { println(it) }
    }

}

