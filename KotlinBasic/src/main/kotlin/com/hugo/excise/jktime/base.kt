package com.hugo.excise.jktime

fun main(args: Array<String>) {
    val i = 12
    val j: Long = i.toLong()

    val name = "Kotlin"
    print("Hello $name")

    val array = arrayOf("Java", "Kotlin")
    print("Hello ${array.get(1)}!")

    val s = """
       当我们的字符串有复杂的格式时
       原始字符串非常的方便
       因为它可以做到所见即所得。 """
    print(s)

    val arraystring = arrayOf("apple", "pear")
    println("Size is ${arraystring.size}")
    println("First element is ${arraystring[0]}")

    /*
     关键字    函数名          参数类型   返回值类型
      ↓        ↓                ↓       ↓      */
    fun helloFunction(name: String): String {
        return "Hello $name !"
    }/*   ↑
     花括号内为：函数体
    */

    createUser(
        name = "Tom",
        age = 30,
        commentCount = 3285
    )

    val i2: Int = 1
    val message = when(i2) {
        1 -> "一"
        2 -> "二"
        else -> "i2 不是一也不是二" // 如果去掉这行，会报错
    }
    print(message)

}

fun createUser(
    name: String,
    age: Int,
    gender: Int = 1,
    friendCount: Int = 0,
    feedCount: Int = 0,
    likeCount: Long = 0L,
    commentCount: Int = 0
) {

}

fun getLength(text: String?): Int {
    return if (text != null) text.length else 0
    // 等价：return text?.length ?: 0
}