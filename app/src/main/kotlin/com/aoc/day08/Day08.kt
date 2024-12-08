package com.aoc.day08

import Coord
import dropIndexN
import repeat

fun String.toCity(): City {
    val map = mutableMapOf<Coord,Char>()
    val reverse = mutableMapOf<Char,List<Coord>>()
    this.lines().forEachIndexed{ y, line ->
        line.forEachIndexed { x, char ->
            if (char != '.') {
                map.put(Coord(x, y), char)
                reverse.compute(char) { antenna, coords -> (coords ?: listOf()) + Coord(x, y) }
            }
        }
    }
    val width = this.lines()[0].length
    val height = this.lines().size

    return City(map, width, height, reverse)
}

data class City(val map: Map<Coord, Char>, val width: Int, val height: Int, val reverse: Map<Char,List<Coord>>) {

    fun calcAntiNodes(calcAntiNodes : (Coord,Coord)-> List<Coord> = ::calcAntiAntenna ) : Set<Coord> {
        val antiNodes = mutableMapOf<Char, Set<Coord>>()
        reverse.entries.forEach { (antenna, coords) ->
            coords.indices.forEach { i ->
                val other = coords.dropIndexN(i)
                if (other.isNotEmpty()) {
                    coords[i].repeat(other.size).toList().zip(other).forEach { (a, b) ->
                        calcAntiNodes(a,b).forEach { antinodes ->
                            antiNodes.compute(antenna) { _, v -> (v ?: setOf()) + antinodes }
                        }
                    }
                }
            }
        }

        return antiNodes.values.flatten().toSet()
    }

    fun part1() = calcAntiNodes().size
    fun part2() = calcAntiNodes(::calcAntiAntenna2).size

    fun isOutsideBounds(coord: Coord) : Boolean =
        coord.x < 0 || coord.x > width-1 || coord.y < 0 || coord.y > height-1

    fun calcAntiAntenna(a : Coord, b : Coord) : List<Coord> {
        val vector = Coord(b.x-a.x, b.y-a.y)
        val anetenna1 = b + vector
        val anetenna2 = a + vector.inverse()

        return listOf(anetenna1, anetenna2).filterNot(::isOutsideBounds)
    }

    private fun getAllAntiNodes(from: Coord, direction: Coord) : List<Coord> = generateSequence(from) {
        it + direction
    }.takeWhile { !isOutsideBounds(it) }.toList()

    fun calcAntiAntenna2(a : Coord, b : Coord) : List<Coord> {
        val vector = Coord(b.x-a.x, b.y-a.y)
            return listOf(getAllAntiNodes(b, vector),
        getAllAntiNodes(a, vector.inverse())).flatten()
    }
}

