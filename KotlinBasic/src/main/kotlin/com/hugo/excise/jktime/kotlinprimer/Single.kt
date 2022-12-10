package com.hugo.excise.jktime.kotlinprimer

/**
 * @author hugo
 * @date 2022-12-10 12:26
 */
class Single private constructor() {
    companion object {
        init {

        }

        fun get(): Single {
            return Holder.instance
        }
    }

    private object Holder {
        init {
            println("holder")
        }

        val instance = Single()
    }
}

fun main(args: Array<String>) {
    Single.get()
}