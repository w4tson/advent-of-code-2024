package com.aoc.day11

import java.math.BigInteger

fun blinkSequence(input : String): Sequence<Map<String, BigInteger>> {
    val stonesMap = input.split(" ").associate { Pair(it, BigInteger("1")) }
    var blink = 0
    return generateSequence(stonesMap) {
        blink++
        
        it.entries.flatMap { (stone, num) ->
            when{
                stone == "0" -> listOf(Pair("1", num))
                stone.length % 2 == 0 -> {
                    val lhs = stone.slice(0 until(stone.length/2))
                    val rhs = stone.slice((stone.length/2)until stone.length)
                    listOf(Pair(lhs,num), Pair("${rhs.toLong()}", num))
                }
                else -> listOf(Pair("${stone.toLong() * 2024}", num))
            }
        }.fold(mutableMapOf()) { acc, pair ->
            acc.compute(pair.first) {
                k, v ->
                val existing = v ?: BigInteger.ZERO
                existing.add(pair.second)
            }
            acc
        }
    }
}

fun afterNBlinks(input : String, n: Int): BigInteger {
    return blinkSequence(input).take(n + 1).last().values.fold(BigInteger.ZERO) { acc, item ->
        acc.add(item)
    }
}

