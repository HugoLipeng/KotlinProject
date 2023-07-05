package com.hugo.excise.kotlincn

/**
 * @author hugo
 * @date 2023-07-05 16:43
 */
fun main() {
    val a: Int = 1  // 立即赋值
    val b = 2   // 自动推断出 `Int` 类型
    val c: Int  // 如果没有初始值类型不能省略
    c = 3       // 明确赋值
    println("a=$a,b=$b,c=$c")

    val items = listOf("apple", "banana", "kiwifruit")
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }

    println(sum(1, 2))

    println(describe(1))
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun describe(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }