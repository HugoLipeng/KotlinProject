package com.hugo.excise.jktime.kotlinprimer

/**
 * @author hugo
 * @date 2022-12-25 15:05
 * Kotlin的真泛型和实现方式
 */

class Test<T> where T : Callback, T : Runnable {
    fun add(t: T) {
        t.run()
        t.callback()
    }
}

open class A : Runnable {
    override fun run() {
        println("run")
    }
}

class B : Callback, A() {
    override fun callback() {
        println("callback")
    }
}

interface Callback {
    fun callback()
}

fun main(args: Array<String>) {
    val test = Test<B>()
    test.add(B())
}