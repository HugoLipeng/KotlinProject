package com.hugo.excise

fun main(){
    val map = mapOf("apple" to 1,"banana" to 2,"Orange" to 3)
    for ((fruit,number) in map){
        println("fruit is $fruit,number is $number")
    }
}