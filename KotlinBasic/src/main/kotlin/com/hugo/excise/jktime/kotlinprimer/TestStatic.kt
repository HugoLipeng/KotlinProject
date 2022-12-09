package com.hugo.excise.jktime.kotlinprimer

object TestStatic {
    @JvmStatic
    fun sayMessage(msg: String) {
        println(msg)
    }
}
