package com.hugo.excise.jktime.kotlinprimer.coroutine

import kotlinx.coroutines.*

/**
 * @author hugo
 * @date 2022-12-25 16:58
 */

fun main(args: Array<String>) = runBlocking<Unit> {

    val job = launch {
        println("launch..." + Thread.currentThread().name)
    }

    val job2 = async(CommonPool) {
        delay(500L)
        println("async..." + Thread.currentThread().name)
        return@async "hello"
    }

    println("job2的输出：" + job2.await())
    delay(1300L)
}