package com.aoc.day10

import Coord

data class TopoMap(val map: Map<Coord, Int>, val width: Int, val height: Int) {
    fun trailHeads() : List<Coord> = map.entries.filter { it.value == 0 }.map { it.key }

    fun part1() = trailHeads().sumOf { walkFrom(it).map { it.last() }.toSet().count() }
    fun part2() = trailHeads().sumOf { walkFrom(it).count() }

    fun score(trailHead : Coord) : Int = walkFrom(trailHead).count()

    fun walkFrom(start: Coord): Set<List<Coord>> {
        val search = ArrayDeque<List<Coord>>()
        search.add(listOf(start))
        val foundPaths = mutableSetOf<List<Coord>>()

        while (search.isNotEmpty()) {
            val currentPath = search.removeFirst()
            val current = currentPath.last()
            val level = map[current]!!
            if (level == 9) {
                foundPaths.add(currentPath)
                continue
            }

            current.adjacentCoords()
                .filter { it.withBounds(width, height) && map.getOrDefault(it, -1) == (level+1) && !currentPath.contains(it) }
                .forEach { search.add(currentPath+it) }
        }
        return foundPaths
    }
}

