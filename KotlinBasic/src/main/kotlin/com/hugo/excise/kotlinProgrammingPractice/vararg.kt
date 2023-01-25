package com.hugo.excise.kotlinProgrammingPractice


fun max(vararg numbers: Int): Int {
    var large = Int.MIN_VALUE
    for (number in numbers) {
        large = if (number > large) number else large
    }
    return large
}

fun greetMany(msg: String, vararg names: String) {
    println("$msg ${names.joinToString(", ")}")
}

fun main() {
    println(max(1, 5, 2)) // 5
    println(max(1, 5, 2, 12, 7, 3)) // 12
    greetMany(msg = "Hello", "Tom", "Jerry", "Spike")
}

