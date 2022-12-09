package com.hugo.excise.base

fun main(){
    println("hello world")
    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a"

    a = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)

    maxOf(3,4)
    println("${maxOf(3, 4)}")
    println("${printProduct("5","6")}")

    fun printLength(obj: Any) {
        println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
    }
    printLength("Incomprehensibilities")
    printLength(1000)
    printLength(listOf(Any()))

    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items){
        println(item)
    }

    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))

    val x = 10
    val y = 9
    if(x in 1..y+1) {
        println("fits in range")
    }

    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }
}

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun parseInt(str: String): Int?{
    return str.toIntOrNull()
}

fun printProduct(arg1: String,arg2: String){
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    if(x != null && y != null){
        println(x * y)
    }else{
        println(null)
    }
}


fun getStringLength(obj: Any): Int? {
//    if(obj is String) {
//        //`obj` 在该条件分支内自动转换成 `String`
//        return obj.length
//    }
//
//    return null
    if(obj !is String) return null

    return obj.length
}


fun describe(obj: Any): String =
    when (obj) {
        1          -> "One"
        "Hello"    -> "Greeting"
        is Long    -> "Long"
        !is String -> "Not a string"
        else       -> "Unknown"
    }

















