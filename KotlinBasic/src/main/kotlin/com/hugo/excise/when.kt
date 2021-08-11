package com.hugo.excise

fun main(args: Array<String>) {
    val x = 5
    when(x){
        is Int -> println("Hello $x")
        in 1..100 -> println("$x is in 1..100")
        !in 1..100 -> println("$x is not in 1..100")
        args[0].toInt() -> println("x == args[0]")
    }

    val mode = when{
        args.isNotEmpty() && args[0] == "1" -> 1
        else -> 0
    }
    val score = getScore("Tom")
    println(score)
}

fun getScore(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    else -> 0
}