package com.aoc.day03
import com.aoc.day03.Action.DO
import com.aoc.day03.Action.DONT

fun part1(input: String) : Int {
    return """mul\((\d{1,3}),(\d{1,3})\)""".toRegex().findAll(input).sumOf {
        val (a, b ) = it.destructured
        a.toInt() * b.toInt()
    }
}

enum class Action { DO, DONT }
data class State(val dos: List<String>, val donts: List<String>, val action : Action)

fun part2(input: String) : Int {
    val instructions = """(mul\((\d{1,3}),(\d{1,3})\)|don't\(\)|do\(\))""".toRegex().findAll(input).map { it.value }
    val parsed = instructions.fold(State(listOf(), listOf(), DO)){ state, item ->
        if (item.startsWith("mul")){
            if (state.action == DO) {
                State(state.dos+item, state.donts, state.action)
            } else {
                State(state.dos, state.donts+item, state.action)
            }
        } else if (item == "do()"){
            State(state.dos, state.donts, DO)
        } else {
            State(state.dos, state.donts, DONT)
        }
    }

    return part1(parsed.dos.joinToString())
 }