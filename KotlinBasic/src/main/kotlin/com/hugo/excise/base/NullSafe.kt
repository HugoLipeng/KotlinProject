package com.hugo.excise.base

fun getName(): String?{
    return null
}

fun main(args: Array<String>) {
    val value: String? = "HelloWorld"
    println(value!!.length)

    val name: String = getName() ?: return
    println(name.length)
}