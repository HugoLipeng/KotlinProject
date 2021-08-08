package com.hugo.excise.basics

/**
 * 高阶函数
 */
fun main(args: Array<String>) {
    args.forEach(::println)

    val helloWorld = Hello::world

    args.filter(String::isNotEmpty)

    val pdfPrinter = PdfPrinter()
    args.forEach(pdfPrinter::println)
}

class PdfPrinter{
    fun println(any: Any){
        kotlin.io.println(any)
    }
}

class Hello{
    fun world(){
        println("Hello World.")
    }
}