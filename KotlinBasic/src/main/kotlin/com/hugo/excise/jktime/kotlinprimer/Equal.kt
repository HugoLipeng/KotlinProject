package com.hugo.excise.jktime.kotlinprimer

/**
 * @author hugo
 * @date 2022-12-23 22:24
 * Kotlin中如何比较对象 === 对象是否相同 == 对象的值是否相同
 */

fun main(s: Array<String>) {

    val string = "string"
//    val javaString = String("string")
    val newString = StringBuilder("string").toString()
    val newString2 = String("string".toByteArray())

    println(string === newString)
    println(string == newString)
    println(string === newString2)
    println(string == newString2)
}