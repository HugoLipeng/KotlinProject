package com.hugo.excise

/**
 * 可变参数 vararg
 */
fun main(vararg args: String) {
//    for (arg in args){
//        println(arg)
//    }

    val list = arrayListOf(1,3,4,5)
    val array = intArrayOf(1,3,4,5)
    hello(3.0, *array)
}

fun hello(double: Double, vararg ints: Int, string: String = "Hello"){
    println(double)
    ints.forEach(::println)
    println(string)
}