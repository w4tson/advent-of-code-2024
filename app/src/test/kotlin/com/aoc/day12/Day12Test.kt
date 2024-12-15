package com.aoc.day12

import Coord
import Util.Companion.readInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day12Test {

    val input = readInput("/day12/day12.txt")
    val testInput = readInput("/day12/day12-test.txt")

    val testStr = """
        AAAA
        BBCD
        BBCC
        EEEC
    """.trimIndent()

    val testStr2 = """
        RRRRIICCFF
        RRRRIICCCF
        VVRRRCCFFF
        VVRCCCJFFF
        VVVVCJJCFE
        VVIVCCJJEE
        VVIIICJJEE
        MIIIIIJJEE
        MIIISIJEEE
        MMMISSJEEE
    """.trimIndent()

    val testStr3 = """
        FFFFFFFFFFCCQ
        EEEEEETTYYYYY
        EEEEEETTYYYYY
        EEEESEEEYYYYY
    """.trimIndent()



    @Test
    fun test1() {
        testStr.toFarm().findRegions().also {
            it.forEach { println(it) }
        }

        val farm = testStr.toFarm()
        farm.findRegions()
        assertEquals(80, farm.price())

        val farm2 = testStr2.toFarm()
        farm2.findRegions()
        assertEquals(1930, farm2.price())



    }

    @Test
    fun smalltest() {
        val farm = testStr3.toFarm()
        farm.findRegionAt(Coord(6,2))
        print(farm.regions)
        //farm.price().also { println(it) }
    }

    @Test
    fun exact() {
        val farm = input.toFarm()
        farm.findRegionAt(Coord(50,36))
        print(farm.regions)
        //farm.price().also { println(it) }
    }

    @Test
    fun part2() {
        val farm = input.toFarm()
        farm.findRegions()
        farm.price().also { println(it) }
    }
}

fun String.toFarm(): Farm {
    val map = mutableMapOf<Coord, Char>()
    val lines = this.lines()
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            map[Coord(x,y)] = char
        }
    }
    return Farm(map, lines[0].length, lines.size)
}

