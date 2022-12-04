package com.hugo.excise.base


fun main(args: Array<String>) {
    function()
    var quantity = 5
    val price: Double = 20.5
    val name: String = "Rice"

    println("单价:$price")
    println("数量:$quantity")
    println("产品:$name 总计:${quantity * price}")

    for (x in 1..9) {
        println(x)
    }

    if ("R" in name) {
        println("Yes")
    }

    cases("hello")

//    int[] array = new int[]{1,2,3};  // Java Array
    var array: IntArray = intArrayOf(1,2,3)
}

fun cases(obj: Any) {
    when (obj) {
        1 -> println("第一项")
        "hello" -> println("这是一个字符串hello")
        is Long -> println("这是一个Long类型数据")
        !is String -> println("这不是String类型的数据")
        else -> println("else类似于Java中的default")
    }
}

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