package com.hugo.excise.base

// Kotlin 的 Lambda 是一个匿名对象
// 可以使用 inline 修饰方法，这样当方法在编译 时就会拆解方法的调用为语句调用，进而减少 创建不必要的对象
inline fun onlyif(isDebug: Boolean, block: () -> Unit) {
    if (isDebug) block()
}

fun main(args: Array<String>) {
    // 当Lambda{}作为函数的最后一个参数时,可以将{}写到函数传参的小括号()之外
    onlyif(true){
        println("print log")
    }

    val runnable = Runnable {
        println("Runnable::run")
    }

    val function: () -> Unit
    // 对象::方法名 ==> 函数的声明
    function = runnable::run

    onlyif(true, function)
}