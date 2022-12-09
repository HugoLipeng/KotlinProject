package com.hugo.excise.jktime.kotlinprimer

import java.io.File


fun main() {
    TestStatic.sayMessage("hello")

    val file = File("src/main/kotlin/com/hugo/excise/jktime/kotlinprimer/test.txt")
    println(file.readText())
}
