package com.hugo.excise.base

/**
 * 可变参数 vararg
 */
fun main(vararg args: String) {
//    for (arg in args){
//        println(arg)
//    }

    val list = arrayListOf(1, 3, 4, 5)
    val array = intArrayOf(1, 3, 4, 5)
    hello(3.0, *array)

    fun printAll(vararg messages: String) {
        for (m in messages) println(m)
    }
    printAll("Hello", "Hallo", "Salut", "Hola", "你好")

    fun printAllWithPrefix(vararg messages: String, prefix: String) {
        for (m in messages) println(prefix + m)
    }
    printAllWithPrefix(
        "Hello", "Hallo", "Salut", "Hola", "你好",
        prefix = "Greeting: "
    )

    fun log(vararg entries: String) {
        printAll(*entries)
    }
    log("Hello", "Hallo", "Salut", "Hola", "你好")
}

fun hello(double: Double, vararg ints: Int, string: String = "Hello") {
    println(double)
    ints.forEach(::println)
    println(string)
}