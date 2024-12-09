package com.aoc

import com.aoc.day09.Node

class LinkedList<T>() : Iterable<T> {
    private var head : Node<T>? = null
    private var tail : Node<T>? = null
    private var size = 0

    fun push(value : T) : LinkedList<T> = apply {
        val oldHead = head
        head = Node(value = value, nextNode = oldHead)
        oldHead?.prevNode = head

        if (tail == null){
            tail = head
        }
        size ++
    }

    fun isEmpty() = size == 0

    fun first() = head

    fun replace(old: Node<T>, new : Node<T>) : LinkedList<T> {
        val before = old.prevNode
        val after = old.nextNode
        before?.nextNode = new
        after?.prevNode = new
        new.prevNode = before
        new.nextNode = after

        old.prevNode = null
        old.nextNode = null
        return this
    }

    fun append(value: T): LinkedList<T> = apply{
        if (isEmpty()){
            push(value)
            return this
        }
        val newNode = Node(value = value)
        newNode.prevNode = tail
        tail?.nextNode = newNode
        tail= newNode

        size++
    }

    fun remove(predicate: (T) -> Boolean) : LinkedList<T> {
        if (head == null) return LinkedList()


        var curr= head
        while (curr != null) {
            if (!predicate(curr.value)) {
                if (head ==curr ) {
                    head = curr.nextNode
                    curr = curr.nextNode
                    curr?.prevNode = null
                } else {
                    val before = curr.prevNode
                    val after = curr.nextNode
                    before?.nextNode = after
                    after?.prevNode = before
                    curr = after
                }
            } else {
                curr = curr.nextNode
            }
        }
        return this
    }

    fun remove(item: Node<T>) : Node<T> {

        if (item == head) {
            head = head?.nextNode
            head?.prevNode = null
        }
        else if (item == tail) {
            tail = item.prevNode
            tail?.nextNode = null
        } else {
            val before = item.prevNode
            val after = item.nextNode
            before?.nextNode = after
            after?.prevNode = before
        }

        item.prevNode= null
        item.nextNode = null
        return item
    }

    fun removeLast(predicate: (T) -> Boolean): Node<T>? {
        if (tail == null) return null
        var curr = tail

        while (curr != null) {
            if (predicate(curr.value)) {
                return remove(curr)
            }
            curr = curr.prevNode
        }
        return null
    }

    fun sliceLast(n : Int, predicate : (T) -> Boolean) : LinkedList<T>  {
        var endOfSlice = tail
        var startOfSlice = tail
        var counter = n-1
        while (counter> 0) {
            startOfSlice = startOfSlice?.prevNode
            startOfSlice?.value?.let {
                //only count down if we match the predicate
                if (predicate(it)) counter--;
            }
        }
        return LinkedList<T>().apply {
            head = startOfSlice
            tail = endOfSlice
            size = n
        }.remove(predicate)
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            var current : Node<T>? = head
            override fun hasNext(): Boolean {
                return current != null
            }

            override fun next(): T {
                val item = current!!.value
                current = current!!.nextNode
                return item
            }

        }
    }

    override fun toString(): String {
        return if (isEmpty()){
            "Empty List"
        }else{
            head.toString()
        }
    }
}

data class Node<T>(var value : T, var nextNode: Node<T>? = null, var prevNode: Node<T>? = null){

    override fun toString(): String {
        return if (nextNode != null){
            "$value${nextNode.toString()}"
        }else{
            "$value"
        }
    }
}