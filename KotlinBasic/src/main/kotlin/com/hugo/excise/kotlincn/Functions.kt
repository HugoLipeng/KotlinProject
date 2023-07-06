package com.hugo.excise.kotlincn

import com.sun.javafx.geom.Path2D.CornerPrefix

/**
 * @author hugo
 * @date 2023-07-05 21:34
 */
fun printMessage(message: String): Unit {
    println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun multiply(x: Int, y: Int) = x * y

fun main() {
    printMessage("hello")
    printMessageWithPrefix("hello", "log")
    printMessageWithPrefix("hello")
    printMessageWithPrefix(prefix = "log", message = "hello")
    println(sum(1, 2))
    println(multiply(2, 4))
}