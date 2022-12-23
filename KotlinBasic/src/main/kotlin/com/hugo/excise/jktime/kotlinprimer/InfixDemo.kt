package com.hugo.excise.jktime.kotlinprimer

/**
 * @author hugo
 * @date 2022-12-23 22:06
 * 中缀表达式
 */

sealed class CompareResult {
    object LESS : CompareResult() {
        override fun toString(): String {
            return "小于"
        }
    }

    object MORE : CompareResult() {
        override fun toString(): String {
            return "大于"
        }
    }

    object EQUAL : CompareResult() {
        override fun toString(): String {
            return "等于"
        }
    }
}

infix fun Int.vs(num: Int): CompareResult =
    if (this - num < 0) {
        CompareResult.LESS
    } else if (this - num > 0) {
        CompareResult.MORE
    } else {
        CompareResult.EQUAL
    }

infix fun Double.vs(num: Int): CompareResult =
    if (this - num < 0) {
        CompareResult.LESS
    } else if (this - num > 0) {
        CompareResult.MORE
    } else {
        CompareResult.EQUAL
    }

infix fun Long.vs(num: Int): CompareResult =
    if (this - num < 0) {
        CompareResult.LESS
    } else if (this - num > 0) {
        CompareResult.MORE
    } else {
        CompareResult.EQUAL
    }

fun main(args: Array<String>) {
    println(5L vs 6)
    println(5.vs(6))
}