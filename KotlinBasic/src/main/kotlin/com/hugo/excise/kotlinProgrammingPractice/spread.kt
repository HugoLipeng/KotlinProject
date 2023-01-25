package com.hugo.excise.kotlinProgrammingPractice


fun main() {
    val values = intArrayOf(1, 21, 3)
    println(max(values[0], values[1], values[2])) // don‘t
    println(max(*values)) // * ==> spread
    println(max(*listOf(1, 4, 18, 12).toIntArray()))
}