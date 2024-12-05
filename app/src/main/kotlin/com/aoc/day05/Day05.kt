package com.aoc.day05


fun String.toPaperSorter() : PrinterSorter {
    val (rulesStr, reportStr) = this.split("\n\n")
    val rulesMap = mutableMapOf<Int,List<Int>>()
    rulesStr.lines().map { line ->
        val (a, b) = line.split("|")
        rulesMap.compute(a.toInt()) { item , value ->
            listOf(b.toInt()) + (value ?: listOf())
        }
    }

    val reports = reportStr.lines().map { line -> line.split(",").map { it.toInt() } }
    return PrinterSorter(rulesMap, reports)
}


fun part1(input : String): Int = input.toPaperSorter().solvePart1()

data class State(val seen : Set<Int>, val valid : Boolean)

data class PrinterSorter(val rules : Map<Int,List<Int>>, val reports: List<List<Int>>) {

    fun solvePart1() : Int = reports.filter(::isValid).sumOf { it[(it.size / 2)] }

    fun isValid(report : List<Int>) : Boolean = report.fold(State(setOf(), true)) { acc, item ->
        if (!acc.valid) {
            acc
        } else {
            val mustComeBefore = rules[item]
            val valid = mustComeBefore?.none { acc.seen.contains(it) } ?: true

            State(acc.seen + item, valid)
        }
    }.valid

    fun solvePart2(): Int {
        return reports.filter { !isValid(it) }.map {
            sort(it)
        }.sumOf { it[(it.size / 2)] }
    }

    fun sort(report : List<Int>) : List<Int> {
        var sortedList = mutableListOf<Int>()

        report.forEach { item ->
            val indexToInsert = sortedList.indices.firstOrNull { index ->
                var tempList = sortedList.toMutableList()
                tempList.add(index, item)
                isValid(tempList)
            } ?: sortedList.size
            sortedList.add(indexToInsert, item)
        }
        return sortedList
    }
}