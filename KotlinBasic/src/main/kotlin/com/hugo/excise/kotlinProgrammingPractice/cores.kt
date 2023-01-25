package com.hugo.excise.kotlinProgrammingPractice

fun main() {
    println(systemInfo())
}

fun systemInfo(): String {
    return when (val numberofCores = Runtime.getRuntime().availableProcessors()) {
        1 -> "1 core,packing this one to the museum"
        in 2..16 -> "You have $numberofCores cores"
        else -> "SnumberofCores cores!,I want your machine"
    }
}