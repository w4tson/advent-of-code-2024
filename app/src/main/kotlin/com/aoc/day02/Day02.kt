package com.aoc.day02

import dropIndexN
import kotlin.math.absoluteValue
import kotlin.math.sign

fun parse(input: String) : List<List<Int>> = input.lines().map { it.split("\\s+".toRegex()).map(String::toInt) }

fun part1(input : String) : Int = parse(input).count(::isReportValid)

fun isReportValid(report : List<Int>) : Boolean =
    report.windowed(2).map { (a, b) -> a - b }.windowed(2).all { (a,b) -> isValid(a,b) }

fun isValid(a: Int, b: Int) : Boolean =
    (1..3).contains(a.absoluteValue)
    && (1..3).contains(b.absoluteValue)
    && a.sign == b.sign

fun part2(input : String) : Int = parse(input).count { report ->
    isReportValid(report) || report.indices.any { isReportValid(report.dropIndexN(it)) }
}