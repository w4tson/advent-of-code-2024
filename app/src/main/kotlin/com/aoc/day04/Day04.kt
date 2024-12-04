package com.aoc.day04

import Coord

fun String.toWordSearch(): WordSearch {
    val lines = this.lines()
    val wordSearch : MutableMap<Coord, Char> = mutableMapOf()
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
            wordSearch[Coord(x,y)] = c
        }
    }
    return WordSearch(wordSearch, lines[0].length, lines.size)
}

class WordSearch(val input: Map<Coord, Char>, val width: Int, val height: Int) {

    val found : MutableSet<Set<Coord>> = mutableSetOf()

    fun countXmas() : Int {
        (0 until height).forEach{ y->
            (0 until width).forEach{ x->
                findAllXmasAtCoord(Coord(x,y))
            }
        }

        return found.size
    }

    fun countMAS() : Int {
        (0 until height).forEach{ y->
            (0 until width).forEach{ x->
                findAllMASAtCoord(Coord(x,y))
            }
        }

        return found.size
    }

    fun findAllXmasAtCoord(coord: Coord) {
        coord.fourCoordsIn8Directions().filter { listOf4 ->
            val result = listOf4.map { input.getOrDefault(it, '_') }.joinToString(separator = "")
            result == "XMAS" || result == "SAMX"
        }.map { it.toSet() }
        .forEach { found.add(it) }
    }

    fun findAllMASAtCoord(coord: Coord) {
        val foundMas = coord.threeCoordsIn2DiaganolDirections().all { listOf3 ->
            val result = listOf3.map { input.getOrDefault(it, '_') }.joinToString(separator = "")
            result == "MAS" || result == "SAM"
        }
        if (foundMas){
            found.add(setOf(coord))
        }
    }

}
