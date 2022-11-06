package com.hugo.excise

import com.hugo.excise.inter.Study

class Student(name: String, age: Int) : Person(name, age), Study {
    override fun readBooks() {
        println("$name is reading.")
    }

    override fun doHomework() {
        println("$name is doing homework.")
    }
}