package com.hugo.excise.jktime.kotlinprimer

import com.google.gson.Gson
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

inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json, T::class.java)
}

class View<T>(val clazz: Class<T>) {
    val presenter by lazy { clazz.newInstance() }

    companion object {
        inline operator fun <reified T> invoke() = View(T::class.java)
    }
}

class Presenter {
    override fun toString(): String {
        return "presenter"
    }
}

fun main(args: Array<String>) {
    val test = Test<B>()
    test.add(B())

    val b = View<Presenter>().presenter
    val a = View.Companion.invoke<Presenter>().presenter
    println(a)
    println(b)
}