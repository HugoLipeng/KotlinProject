package com.hugo.excise.kotlinProgrammingPractice

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
    print("\n")
    for (ch in 1 until 10 step 2) {
        print("$ch, ")
    }
    print("\n")
    for (ch in 10 downTo 1 step 2) {
        print("$ch, ")
    }
}
