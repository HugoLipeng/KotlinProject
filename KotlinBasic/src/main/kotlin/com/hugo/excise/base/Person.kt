package com.hugo.excise.base

class Person(val name: String){

    fun printName(){
        println(name)
    }
}

fun main(args: Array<String>) {
    Person("hugo").printName()
}