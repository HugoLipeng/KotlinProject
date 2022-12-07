package com.hugo.excise.base

class Person(private var name: String) {

    private var description: String? = null

    init {
        name = "Hugo"
    }

    constructor(name: String, description: String) : this(name) {
        this.description = description
    }

    internal fun printName() {
        println("my name is $name")
    }
}

fun main(args: Array<String>) {
    Person("hugo").printName()
}