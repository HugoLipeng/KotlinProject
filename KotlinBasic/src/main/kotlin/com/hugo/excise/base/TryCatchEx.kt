package com.hugo.excise.base

fun main(args: Array<String>) {
    val result = try {
        args[0].toInt() / args[1].toInt()
    } catch (e: Exception) {
        e.printStackTrace()
        println("catch in")
    }
    println(result)
}