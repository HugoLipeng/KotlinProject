package com.hugo.excise.KotlinProgrammingPractice

fun getFullName() = Triple("John", "Quincy", "Adams")

fun main() {
//    val result = getFullName()
//    val first = result.first
//    val middle = result.second
//    val last = result.third
    val (first, middle, last) = getFullName()
    println("$first $middle $last")
}