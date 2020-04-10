package com.hugo.excise

/**
 * companion object：伴生对象

只能放在class中，且单个class中只能存在一个，类似人生来就有个嘴巴，然后只能有一个，嘴巴就是人的伴生对象。

主要用来表示类中的常量集合，解决kotlin中没有static的问题，其作用类似于顶级函数和顶级属性。
 */
fun main(args: Array<String>) {
    val latitude = Latitude.ofDouble(3.0)
    val latitude2 = Latitude.ofLatitude(latitude)

    println(Latitude.TAG)
}

class Latitude private constructor(val value: Double){
    companion object{
        @JvmStatic
        fun ofDouble(double: Double): Latitude{
            return Latitude(double)
        }

        fun ofLatitude(latitude: Latitude): Latitude{
            return Latitude(latitude.value)
        }

        @JvmField
        val TAG: String = "Latitude"
    }
}