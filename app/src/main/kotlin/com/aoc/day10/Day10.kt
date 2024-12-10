package com.aoc.day10

import Coord

data class State(val locations: List<Coord>, val isValid: Boolean = true)

data class TopoMap(val map: Map<Coord, Int>, val width: Int, val height: Int) {
    fun trailHeads() : List<Coord> = map.entries.filter { it.value == 0 }.map { it.key }

    fun part1() = trailHeads().sumOf { walkFrom(it).count() }

    fun score(trailHead : Coord) : Int = walkFrom(trailHead).count()

    fun walkFrom(start: Coord): Set<Coord> {
        val search = ArrayDeque<Coord>()
        search.add(start)
        val visited = mutableSetOf(start)
        val found = mutableSetOf<Coord>()
        while (search.isNotEmpty()) {
            val current = search.removeFirst()
            val level = map[current]!!
            visited.add(current)
            if (level == 9) {
                found.add(current)
                continue
            }



            current.adjacentCoords()
                .filter { it.withBounds(width, height) && map.getOrDefault(it, -1) == (level+1) && !visited.contains(it) }
                .filter { !search.contains(it) }
                .forEach { search.add(it) }
//            println("visited= $visited")
//            println("search = $search")
        }
        return found

    }


}

