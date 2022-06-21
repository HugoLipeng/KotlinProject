package com.hugo.excise.jktime

fun main(args: Array<String>) {
    val i = 12
    val j : Long = i.toLong()

    val name = "Kotlin"
    print("Hello $name")

    val array = arrayOf("Java", "Kotlin")
    print("Hello ${array.get(1)}!")

    val s = """
       当我们的字符串有复杂的格式时
       原始字符串非常的方便
       因为它可以做到所见即所得。 """
    print(s)
}