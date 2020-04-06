package com.hugo.excise

fun function() {
    val str = "hello!"

    fun say(count: Int = 10) {
        println(str)
        if (count > 0) {
            say(count - 1)
        }
    }
    say()
}

fun print(name: String = "Hugo"): String? {
    println("$name")
    return name
}

fun main(args: Array<String>) {
    function()
}