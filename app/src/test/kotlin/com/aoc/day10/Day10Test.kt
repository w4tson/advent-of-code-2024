package com.aoc.day10

import Coord
import Util.Companion.readInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test



class Day10Test {

    val input = readInput("/day10/day10.txt")
    val testInput = readInput("/day10/day10-test.txt")

    val testStr = """
        0123
        1234
        8765
        9876
    """.trimIndent()

    val testStr2 = """
        ...0...
        ...1...
        ...2...
        6543456
        7.....7
        8.....8
        9.....9
    """.trimIndent()

    val testStr3 = """
        ..90..9
        ...1.98
        ...2..7
        6543456
        765.987
        876....
        987....
    """.trimIndent()

    val testStr4 = """
        10..9..
        2...8..
        3...7..
        4567654
        ...8..3
        ...9..2
        .....01
    """.trimIndent()

    val testStr5 = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent()

    val testStr6 = """
        .....0.
        ..4321.
        ..5..2.
        ..6543.
        ..7..4.
        ..8765.
        ..9....
    """.trimIndent()

    val testStr7 = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent()

    @Test
    fun test1() {
        assertEquals(1, testStr.toTopoMap().score(Coord(0, 0)))
        assertEquals(2, testStr2.toTopoMap().score(Coord(3, 0)))
        assertEquals(4, testStr3.toTopoMap().score(Coord(3, 0)))
        assertEquals(1, testStr4.toTopoMap().score(Coord(1, 0)))
        assertEquals(2, testStr4.toTopoMap().score(Coord(5, 6)))
        assertEquals(36, testStr5.toTopoMap().part1())

    }

    @Test
    fun part2Test() {
        assertEquals(3, testStr6.toTopoMap().walkFrom(Coord(5, 0)).count())
        assertEquals(81, testStr7.toTopoMap().part1())

    }

    @Test
    fun part1() {
        input.toTopoMap().part1().also { println(it) }
    }

    @Test
    fun part2() {
        input.toTopoMap().part2().also { println(it) }
    }
}

fun String.toTopoMap(): TopoMap {
    val map = mutableMapOf<Coord, Int>()
    this.lines().forEachIndexed{
        y, line ->
        line.forEachIndexed{ x, ch ->
            val value = if (ch == '.') -1 else ch.digitToInt()
            map.put(Coord(x,y), value)
        }
    }
    return TopoMap(map, this.lines()[0].length, this.lines().size)
}

