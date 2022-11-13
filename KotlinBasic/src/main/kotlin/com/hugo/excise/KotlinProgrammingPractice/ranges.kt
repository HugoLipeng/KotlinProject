package com.hugo.excise.KotlinProgrammingPractice

fun main() {
    // 正向迭代
    for (ch in 'a'..'z') {
        print(ch)
    }
    print("\n")
    // 反向迭代
    for (ch in 'z' downTo 'a') {
        print(ch)
    }
}
