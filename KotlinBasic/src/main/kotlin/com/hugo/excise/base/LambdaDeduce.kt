package com.hugo.excise.base

fun main(){
    val list= listOf("Apple","Banana","Orange","Pear","Grape","Waterm elon")
    var maxLengthFruit =""
    // 原始实现 如何在一个水果集合里面找到单词最长的那个水果
    for (fruit in list){
        if (fruit.length > maxLengthFruit.length){
            maxLengthFruit = fruit
        }
    }
    println("max length fruit is $maxLengthFruit")
    // 使用使用集合的函数式API 实现
    maxLengthFruit = list.maxByOrNull { it.length }.toString()
    // Lambda 推导
    /*
    maxLengthFruit = list.maxByOrNull({fruit:String -> fruit.length })
    ----
    maxLengthFruit = list.maxByOrNull{fruit:String -> fruit.length }
    ----
    maxLengthFruit = list.maxByOrNull({fruit -> fruit.length }
    ----
    maxLengthFruit = list.maxByOrNull({it.length }
     */

}