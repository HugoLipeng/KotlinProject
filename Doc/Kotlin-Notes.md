### Kotlin入门指南

Kotlin是一门现代的静态类型编程语言,可以编译到Java字节码,并且与Java代码完全互操作。它由JetBrains开发,目的是提高开发人员的生产力。

Hello World程序:

```kotlin
fun main() { 
    println("Hello, World!") 
}
```

变量声明:

Kotlin是一门静态类型的语言,所以我们需要指定变量的类型。使用`var`关键字声明变量:

```kotlin
var myVariable: Int = 10
```

我们也可以使用`val`声明只读变量:

```kotlin
val myConstant: String = "Hello"
```

基本类型:

- Int
- Double
- Boolean
- Char
- String

条件表达式:

使用`if`表达式:

```kotlin
if (age > 18) {
    println("Eligible to vote") 
} else {
    println("Not eligible to vote")
}
```

也可以使用`when`表达式:

```kotlin
when (age) {
    18 -> println("Just became eligible to vote!")
    in 19..64 -> println("Eligible to vote")
    65 -> println("Eligible for senior discounts!")
    else -> println("Not eligible to vote") 
}
```

循环:

Kotlin支持`for`循环和`while`循环:

```kotlin
for (i in 1..10) {
    println(i) 
}

var i = 1 
while (i <= 10) { 
    println(i) 
    i++ 
}
```

函数:

使用`fun`关键字定义函数:

```kotlin
fun square(x: Int): Int { 
    return x * x 
}
```

我们也可以使用表达式定义函数:

```kotlin 
fun square(x: Int) = x * x
```



类和对象:

Kotlin支持面向对象的程序设计。使用`class`关键字定义一个类:

```kotlin
class Person(var name: String) {
    var age = 0

    fun eat() {
        println("$name is eating")
    }
}
```

我们可以使用`obj`关键字定义对象:

```kotlin
object Car {
    var name = "Ford"
    fun drive() {
        println("$name is driving")
    }
}
```

继承:

Kotlin支持继承,使用`:`表示继承关系:

```kotlin
open class Animal {
    open fun eat() {
        println("Animal is eating")
    }
}

class Dog : Animal() {
    override fun eat() {
        println("Dog is eating")
    }
}

val dog = Dog()
dog.eat() // Prints "Dog is eating"
```

抽象类:

可以使用`abstract`关键字定义抽象类和抽象方法:

```kotlin
abstract class Vehicle {
    abstract fun startEngine()
    abstract fun stopEngine()
}

class Car : Vehicle() {
    override fun startEngine() {
        println("Car engine started")
    }
    override fun stopEngine() {
        println("Car engine stopped")
    }
}
```

扩展函数:

Kotlin支持使用扩展函数为已有类添加新方法:

```kotlin
fun String.isEmail(): Boolean {
    // ...
}

val email = "abc@example.com"
email.isEmail() // Returns true
```

高阶函数和lambda表达式:

Kotlin支持函数类型,可以接受函数作为参数:

```kotlin
fun operate(x: Int, y: Int, op: (Int, Int) -> Int): Int {
    return op(x, y)
}

val sum = operate(1, 2) { a, b -> a + b }  // sum = 3
```

这里我们传递了一个lambda表达式作为最后一个参数。



泛型:

Kotlin支持泛型,使用`<>`括起来的类型参数定义泛型类或函数:

```kotlin
fun <T> print(items: List<T>) {
    for (item in items) {
        println(item)
    }
}

print(listOf(1, 2, 3)) 
print(listOf("a", "b", "c"))
```

可空类型:

Kotlin有可空和不可空两种类型。使用`?`表示可空类型:

```kotlin
var str: String = "abc"   // 不可空
var str2: String? = null  // 可空
```

针对可空类型,Kotlin有安全调用操作符`?`和驳回空`!!`:

```kotlin
val email: String? = "abc@example.com"
val emailLength = email?.length   // email?.length 返回 Int? 类型
val emailLength2 = email!!.length // 如果 email 为空,会抛出 KotlinNullPointerException
```

注解:

Kotlin支持注解,使用`@`定义注解:

```kotlin
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class Fancy

@Fancy class Foo {
    @Fancy 
    fun bar(@Fancy x: Int) { ... }
}
```

反射:

Kotlin编译后会保留注解信息,我们可以通过反射来访问:

```kotlin
val fooClass = Foo::class
val annotations = fooClass.annotations 
val fancyAnnotation = fooClass.getAnnotation(Fancy::class.java)
// ...
```

DSL构建:

Kotlin支持使用DSL样式来构建复杂对象。比如Android的Anko库就使用DSL来构建UI:

```kotlin
verticalLayout {
    val name = editText()
    button("Say Hello") {
        onClick { 
            toast("Hello, ${name.text}!") 
        }
    }
} 
```

协程:

Kotlin支持协程,可以用来简化异步编程。使用`coroutine`关键字定义一个协程:

```kotlin 
coroutine fun doSomething() { 
    delay(1000) 
    println("Done!") 
}
```

Kotlin有非常丰富的语言特性,这只是冰山一角。希望这些知识可以帮助你深入理解Kotlin!