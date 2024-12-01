package com.aoc.day01

import kotlin.math.absoluteValue

fun parseInput(input: String) : Pair<List<Int>, List<Int>> = input.lines().map {
    val (a,b) = it.split("\\s+".toRegex())
    Pair(a.toInt(), b.toInt())
}.unzip()

fun part1(input : String) : Int {
    val (listA, listB) = parseInput(input)
    return listA.sorted().zip(listB.sorted()).sumOf { (a, b) -> (a - b).absoluteValue }
}

fun part2(input : String) : Int {
    val (listA, listB) = parseInput(input)
    return listA.sumOf { a -> a * listB.count { it == a } }
}
