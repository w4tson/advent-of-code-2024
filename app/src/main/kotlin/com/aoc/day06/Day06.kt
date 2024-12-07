package com.aoc.day06

import Compass
import Compass.*
import Coord
import kotlin.math.max
import kotlin.math.min

data class Line(val from: Coord, val to : Coord) {
    fun xRange() : LongProgression {
        return if (from.x < to.x) {
            from.x+1 .. to.x
        } else {
            from.x-1 downTo  to.x
        }
    }

    fun yRange(): LongProgression {
        return if (from.y > to.y) {
            from.y-1 downTo  to.y
        } else {
            from.y+1 ..  to.y
        }
    }
}

data class LabMap(val obstructions: List<Coord>, val width: Int, val height: Int, val initialGuard: Guard) {

    val visited = mutableSetOf<Line>()
    val newObstacles = mutableSetOf<Coord>()
    var guard = initialGuard

    fun part2(): Int {
        part1()
        visited.forEach {  }
        return newObstacles.size
    }

    fun part1(): Int {

        while (true) {
            val nextPoint = when(guard.direction) {
                NORTH -> obstructions.filter{ it.y < guard.position.y && it.x == guard.position.x }.maxByOrNull { it.y }?.south()
                SOUTH -> obstructions.filter{ it.y > guard.position.y && it.x == guard.position.x}.minByOrNull { it.y }?.north()
                EAST -> obstructions.filter{ it.x > guard.position.x && it.y == guard.position.y}.minByOrNull { it.x }?.west()
                WEST -> obstructions.filter{ it.x < guard.position.x && it.y == guard.position.y }.maxByOrNull { it.x }?.east()
                else -> throw AssertionError("nope")
            }

            val newLine = if (nextPoint == null) { guard.lineToTheEdge(width,height) } else { Line(guard.position, nextPoint) }
            newObstacles.addAll(willIntroduceALoop(newLine))
            visited.add(newLine)


            if (nextPoint == null) {
                break
            } else {
                guard = Guard(nextPoint, guard.direction.rotate90Clockwise())
            }
        }

        return visited.flatMap { it.from.allCoordsBetween(it.to) }.toSet().count()
    }

    fun visited() = visited.filterNot { it.from == initialGuard.position }

    fun willIntroduceALoop(line: Line) : Set<Coord> {
        return when (guard.direction) {
            NORTH -> line.yRange()
                .flatMap { y ->
                    visited().mapNotNull {
                        if (it.from.y == y && it.from.x > line.from.x) {
                            Coord(line.from.x, y-1)
                        } else null
                    }
                }
            SOUTH -> line.yRange()
                .flatMap { y ->
                    visited().mapNotNull {
                        if (it.from.y == y && it.from.x < line.from.x) {
                            Coord(line.from.x, y+1)
                        } else null
                    }
                }
            EAST -> line.xRange()
                .flatMap { x ->
                    visited().mapNotNull {
                        if (it.from.x == x && it.from.y > line.from.y) {
                            Coord(x+1, line.from.y)
                        } else null
                    }
                }
            WEST -> line.xRange()
                .flatMap { x ->
                    visited().mapNotNull { v ->
                        if (v.from.x == x && v.from.y < line.from.y) {
                            Coord(x-1, line.from.y)
                        } else null
                    }
                }
            else -> throw AssertionError("nope")
        }.toSet()
    }

    fun doesCoordIntroduceALoop(line: Coord) : Boolean {
        return when(guard.direction) {
            NORTH -> visited.any { (line.y +1) == it.from.y }
            SOUTH -> visited.any { (line.y -1) == it.from.y }
            EAST -> visited.any { (line.x -1) == it.from.x }
            WEST -> visited.any { (line.x +1) == it.from.x }
            else -> throw AssertionError("nope")
        }
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



