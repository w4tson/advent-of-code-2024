package com.aoc.day15

import Coord
import takeWhileInclusive
import javax.swing.TransferHandler.MOVE

data class Wharehouse(val map: MutableMap<Coord, Char>, val width: Int, val height: Int, val instructions : List<Char>){

    var robot: Coord = map.filter { it.value == '@' }.keys.first()

    fun coordsInDirection(coord: Coord, move: Char) : Sequence<Coord> {
        return generateSequence(coord) {
            it.moveByChar(move)
        }
    }

    fun coordsInDirection2(start: Coord, move: Char) : Sequence<List<Coord>> {
        return generateSequence(listOf(start)) { prev ->
            prev.filterNot { map[it] == '.' }
                .map { it.moveByChar(move) }.flatMap {
                when (map[it]) {
                    '[' -> listOf(it, it.east())
                    ']' -> listOf(it, it.west())
                    else -> listOf(it)
                }
            }.distinct()
        }
    }

    fun moveAll(coords: List<Coord>, move : Char) {
        val toMove = coords
            .map { map.get(it) }
            .zip(coords)
            .filterNot { it.first == '.' }

        coords.filterNot { map[it] == '.' }.forEach { map.put(it, '.') }

        toMove.forEach { (c,coord) ->
            val next = coord.moveByChar(move)
            map.put(next, c!!)
        }
    }

    fun moveRobot(coord: Coord, move: Char) {
//        println("Move $move:")
        val moveLine = calcMoveLine(coord, move)

        if (moveLine.isNotEmpty()) {
            moveAll(moveLine, move)
            robot = robot.moveByChar(move)
        }

//        printMap()
    }

    private fun calcMoveLine(coord: Coord, move: Char): List<Coord> {
        if (move == '^' || move == 'v') {
               val moveLine = coordsInDirection2(coord, move)
                   .takeWhileInclusive { layer ->(!layer.all { map[it] == '.'}) && (!layer.any{ map[it] == '#'  }) }
                   .toList()
//            println(moveLine.last().map { map.get(it) }.joinToString(""))
            return if (moveLine.size > 1 && moveLine.last().all { map[it] != '#' }) {
                moveLine.dropLast(1).flatten()
            } else {
                emptyList()
            }

        } else { //simple case
            val moveLine = coordsInDirection(coord, move)
                .takeWhileInclusive { !listOf('.', '#').contains(map.getOrDefault(it, '.')) }
                .toList()
            return if (moveLine.size > 1 && '.' == map.get(moveLine.last())) {
                moveLine.dropLast(1)
            } else {
                emptyList()
            }
        }
    }

    fun followInstructions() {
        instructions.forEach { instruction -> moveRobot(robot, instruction) }
    }

    fun gpsOfAllBoxes(): Long {
        followInstructions()
        return map.filter { it.value == '[' }.map { 100 * it.key.y + it.key.x }.sum()
    }

    fun printMap() {
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                print(map.get(Coord(x, y)))
            }
            println()
        }
        println()
    }
}


