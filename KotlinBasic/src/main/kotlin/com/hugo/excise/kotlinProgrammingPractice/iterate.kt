package com.hugo.excise.kotlinProgrammingPractice

fun main() {
    val names = arrayOf("Tom", "Jerry", "Spike")
    for (index in names.indices) {
        println("Position of ${names.get(index)} is $index")
    }
    for ((index, name) in names.withIndex()) {
        println("Position of $name is $index")
    }
}