package com.aoc.day12

import Compass
import Compass.*
import Coord

data class Region(val name: Char, val coords: Set<Coord>){
    val permiter = mutableMapOf<Compass, List<Coord>>()

    fun sortLocation(location: Coord, direction : Compass, map: Map<Coord, Char>) {
        if (map.getOrDefault(location, '.') != name) {
            permiter.compute(direction) { k, v ->
                val list = v ?: listOf()
                list + location
            }
        }
    }

    fun perimiterSides(map: Map<Coord, Char>) : Int{
        this.coords.forEach {
            Compass.fourPointsEnumerated().zip(it.adjacentCoords())
                .forEach { (direction, location) ->
                    sortLocation(location, direction, map)
                }
        }

        // now we have each surrounding coord sorted into the directions
        // we should scan for contiguous regions in each direction

        return countPerimeterInDirectionY(permiter[NORTH]) +
        countPerimeterInDirectionY(permiter[SOUTH]) +
        countPerimeterInDirectionX(permiter[EAST]) +
        countPerimeterInDirectionX(permiter[WEST])
    }

    private fun countPerimeterInDirectionY(coords: List<Coord>?) :Int {
        return coords?.groupBy { it.y }
            ?.map {
                val sortedForHieghtY = it.value.sortedBy { it.x }
                sortedForHieghtY.fold(mutableListOf<MutableList<Coord>>()) { acc, item ->
                    if (acc.isEmpty() || acc.last().last().x != (item.x-1)) {
                        acc.add(mutableListOf(item))
                    } else {
                        acc.last().add(item)
                    }
                    acc
                }.count()
            }?.sum() ?: throw AssertionError("no perimiter y")
    }

    private fun countPerimeterInDirectionX(coords: List<Coord>?) :Int {
        return coords?.groupBy { it.x }
            ?.map {
                val sortedForHieghtY = it.value.sortedBy { it.y }
                sortedForHieghtY.fold(mutableListOf<MutableList<Coord>>()) { acc, item ->
                    if (acc.isEmpty() || acc.last().last().y != (item.y-1)) {
                        acc.add(mutableListOf(item))
                    } else {
                        acc.last().add(item)
                    }
                    acc
                }.count()
            }?.sum() ?: throw AssertionError("no perimiter")
    }
}

data class Farm(val map: Map<Coord, Char>, val width: Int, val height: Int) {
    val regions = mutableListOf<Region>()
    val alreadyInARegion = mutableSetOf<Coord>()


//    fun price() : Int = regions.sumOf { it.coords.size * perimiter(it) }
    fun price() : Int = regions.sumOf { it.coords.size * it.perimiterSides(map) }



    fun findRegions() : List<Region> {
        map.keys.forEach {
            if (!alreadyInARegion.contains(it)) {
//                println("Finding region for ${map[it]} at $it")
                findRegionAt(it)
            }
        }
        return regions.toList()
    }

    fun findRegionAt(location: Coord) {
        if (location in alreadyInARegion) return

        val region = mutableSetOf<Coord>()
        region.add(location)
        val charGroup = map[location]!!
//        println("charGroup=$charGroup")
        val toSearch = HashSet<Coord>()
        val visited = mutableSetOf<Coord>()
        toSearch.add(location)

        while (toSearch.isNotEmpty()) {
            val coord = toSearch.first()
            toSearch.remove(coord)
//            println("finding region for $charGroup ${coord} visited=${visited}")
//            println("toSearch=${toSearch.size}")
            visited.add(coord)


            coord.adjacentCoords()
                .filter { it.withBounds(width, height) && map[it] == charGroup }
                .forEach {
                    if (!visited.contains(it)) {
                        toSearch.add(it)
                        region.add(it)
                    }
                }
        }

        regions.add(Region(charGroup, region.toSet()))
        alreadyInARegion.addAll(region)

    }
}
