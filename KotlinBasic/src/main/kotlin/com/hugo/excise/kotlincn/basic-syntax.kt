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

    // 使用 in 操作符来检测某个数字是否在指定区间内。
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits in range")
    }

    // 使用 lambda 表达式来过滤（filter）与映射（map）集合
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.uppercase() }
        .forEach { println(it) }

    // 对一个对象实例调用多个方法 （with）
    val myTurtle = Turtle()
    with(myTurtle) { // 画一个 100 像素的正方形
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }

    // 配置对象的属性（apply） 这对于配置未出现在对象构造函数中的属性非常有用。
    val myRectangle = Turtle().apply {
        length = 4
        breadth = 5
        color = 0xFAFAFA
    }
    println(myRectangle.breadth)
}

fun describe(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }

class Turtle {
    var length: Int = 0
    var breadth: Int = 0
    var color: Int = 0
    fun penDown() {

    }

    fun penUp() {

    }

    fun turn(degrees: Double) {

    }

    fun forward(pixels: Double) {

    }
}