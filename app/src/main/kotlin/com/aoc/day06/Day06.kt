package com.aoc.day06

import Compass
import Compass.*
import Coord

data class Line(val from: Coord, val to : Coord) {
    fun allBetween() : Set<Coord> = from.allCoordsBetween(to)
}

data class LabMap(val obstructions: List<Coord>, val width: Int, val height: Int, val initialGuard: Guard) {
    val visited = mutableMapOf<Line, Int>()
    var guard = initialGuard

    fun part2(): Int {
        run()
        return visited.keys.flatMap { it.allBetween() }.toSet().count {
            LabMap(obstructions + it, width, height, initialGuard).run().isInALoop()
        }
    }

    fun isInALoop() : Boolean = (visited.maxOfOrNull { it.value } ?: 0) > 5

    fun run(): LabMap {
        while (true) {
            val nextPoint = when(guard.direction) {
                NORTH -> obstructions.filter{ it.y < guard.position.y && it.x == guard.position.x }.maxByOrNull { it.y }?.south()
                SOUTH -> obstructions.filter{ it.y > guard.position.y && it.x == guard.position.x}.minByOrNull { it.y }?.north()
                EAST -> obstructions.filter{ it.x > guard.position.x && it.y == guard.position.y}.minByOrNull { it.x }?.west()
                WEST -> obstructions.filter{ it.x < guard.position.x && it.y == guard.position.y }.maxByOrNull { it.x }?.east()
                else -> throw AssertionError("nope")
            }

            val newLine = if (nextPoint == null) { guard.lineToTheEdge(width,height) } else { Line(guard.position, nextPoint) }
            visited.compute(newLine) { _, v -> (v ?: 0) + 1 }

            if (nextPoint == null || isInALoop()) {
                break
            } else {
                guard = Guard(nextPoint, guard.direction.rotate90Clockwise())
            }
        }
        return this
    }

    fun part1(): Int {
        run()
        return visited.keys.flatMap { it.from.allCoordsBetween(it.to) }.toSet().count()
    }
}

data class Guard(val position: Coord, val direction: Compass) {
    fun lineToTheEdge(width: Int, height: Int) : Line {
        return when (direction) {
            NORTH -> Line(position, Coord(position.x, 0))
            SOUTH -> Line(position, Coord(position.x, height -1L))
            EAST -> Line(position, Coord(width-1L, position.y))
            WEST -> Line(position, Coord(0, position.y))
            else -> throw AssertionError("nope")
        }
    }
}



