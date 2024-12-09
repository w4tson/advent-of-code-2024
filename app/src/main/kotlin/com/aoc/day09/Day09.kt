package com.aoc.day09

import com.aoc.LinkedList

sealed class Block

class EmptyBlock : Block() {
    override fun toString() = "."
}
data class File(val name: Int) : Block() {
    override fun toString(): String {
        return "$name"
    }
}


data class Disk(val diskMapStr : String) {
    val linkedList = LinkedList<Block>()

    init {
        diskMapStr
            .toCharArray()
            .map(Char::digitToInt)
            .forEachIndexed { index, value ->
                repeat(value) {
                    val block = if (index % 2 == 0) {
                        File(index / 2)
                    } else {
                        EmptyBlock()
                    }
                    linkedList.append(block)
                }
            }
    }

    fun solve(): Long {
        var curr = linkedList.first()
        while (curr != null) {
            if (curr.value is EmptyBlock) {
                val item = linkedList.removeLast { it is File }
                if (item != null) {
                    linkedList.replace(curr, item)
                    curr = item
                } else {
                    throw AssertionError("whoops")
                }
            }

            curr = curr.nextNode
        }
        return linkedList.filterIsInstance<File>()
            .mapIndexed { index, file -> index * file.name.toLong() }
            .sum()
    }
}

