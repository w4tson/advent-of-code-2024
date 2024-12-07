package com.aoc.day07

fun operators() = listOf('*','+')
fun operators3() = listOf('*','+','|')

data class State(val ops: String, val num: Long)

data class Equation(val expected: Long, val values: List<Long>) {

    fun isTrue(operators: List<Char> = operators()) : Boolean {
        var answers = listOf(State("",values.first()))

        for (i in values.indices.drop(1)) {

            val newAnswers = operators.flatMap { operator ->
                answers.map {
                    when (operator) {
                        '+' -> State(it.ops+'+', it.num + values[i])
                        '*' -> State(it.ops+'*', it.num * values[i])
                        '|' -> State(it.ops+'|', "${it.num}${values[i]}".toLong())
                        else -> throw IllegalArgumentException()
                    }
                }
            }
            answers = newAnswers.filter { it.num <= expected }
            if (answers.isEmpty()) break
        }

        return answers.any { it.num == expected && it.ops.length == values.size-1 }
    }
}

fun part1(input : List<Equation>): Long =
    input.filter { it.isTrue() }.also { println(it.count()) }.sumOf { it.expected }

fun part2(input : List<Equation>): Long =
    input.filter { it.isTrue(operators3()) }.also { println(it.count()) }.sumOf { it.expected }
