package com.hugo.excise.KotlinProgrammingPractice

fun main() {
    println(canVote("Eve", 12))
}

fun canVote(name: String, age: Int): String {
    var status = if (age > 17) "yes, please vote" else "nope, please come back"
    return "$name, $status"
}