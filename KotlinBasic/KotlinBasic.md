# KotlinBasic

## Kotlin基础

### 条件语句

优先使用 `try`、`if` 与 `when` 的表达形式。

```kotlin
return if (x) foo() else bar()

return when(x) {
    0 -> "zero"
    else -> "nonzero"
}
```

优先选用上述代码而不是：

```kotlin
if (x)
    return foo()
else
    return bar()

when(x) {
    0 -> return "zero"
    else -> return "nonzero"
}
```

### Try 是一个表达式

`try` 是一个表达式，意味着它可以有一个返回值：

```kotlin
val a: Int? = try { input.toInt() } catch (e: NumberFormatException) { null }
```

`try`-表达式的返回值是 `try` 块中的最后一个表达式或者是（所有）`catch` 块中的最后一个表达式。 `finally` 块中的内容不会影响表达式的结果。

### Kotlin中的集合

* Pair—两个值的元组。
* Triple—三个值的元组。
* Array—经过索引的﹑固定大小的对象和基元集合。
* List—有序的对象集合。
* Set—无序的对象集合。
* Map—键和值的关联字典或映射。



### 作用域函数 apply/with/run/also/let

Kotlin 提供了一系列用来在给定对象上下文中执行代码块的函数：`let`、 `run`、 `with`、 `apply` 以及 `also`。 关于不同情况下选择正确作用域函数的准则，请参考[作用域函数](https://book.kotlincn.net/text/scope-functions.html)。

| 函数    | 对象引用 | 返回值            | 是否是扩展函数             |
| ------- | -------- | ----------------- | -------------------------- |
| `let`   | `it`     | Lambda 表达式结果 | 是                         |
| `run`   | `this`   | Lambda 表达式结果 | 是                         |
| `run`   | -        | Lambda 表达式结果 | 不是：调用无需上下文对象   |
| `with`  | `this`   | Lambda 表达式结果 | 不是：把上下文对象当做参数 |
| `apply` | `this`   | 上下文对象        | 是                         |
| `also`  | `it`     | 上下文对象        | 是                         |

以下是根据预期目的选择作用域函数的简短指南：

- 对一个非空（non-null）对象执行 lambda 表达式：`let`
- 将表达式作为变量引入为局部作用域中：`let`
- 对象配置：`apply`
- 对象配置并且计算结果：`run`
- 在需要表达式的地方运行语句：非扩展的 `run`
- 附加效果：`also`
- 一个对象的一组函数调用：`with`





## Kotlin概念

- 类型
  - 基本类型
    - [概述](https://book.kotlincn.net/text/basic-types.html)
    - [数字](https://book.kotlincn.net/text/numbers.html)
    - [布尔](https://book.kotlincn.net/text/booleans.html)
    - [字符](https://book.kotlincn.net/text/characters.html)
    - [字符串](https://book.kotlincn.net/text/strings.html)
    - [数组](https://book.kotlincn.net/text/arrays.html)
    - [无符号整型](https://book.kotlincn.net/text/unsigned-integer-types.html)
  - [类型检测与类型转换](https://book.kotlincn.net/text/typecasts.html)
- 控制流程
  - [条件与循环](https://book.kotlincn.net/text/control-flow.html)
  - [返回与跳转](https://book.kotlincn.net/text/returns.html)
  - [异常](https://book.kotlincn.net/text/exceptions.html)
- [包与导入](https://book.kotlincn.net/text/packages.html)
- 类与对象
  - [类](https://book.kotlincn.net/text/classes.html)
  - [继承](https://book.kotlincn.net/text/inheritance.html)
  - [属性](https://book.kotlincn.net/text/properties.html)
  - [接口](https://book.kotlincn.net/text/interfaces.html)
  - [函数式（SAM）接口](https://book.kotlincn.net/text/fun-interfaces.html)
  - [可见性修饰符](https://book.kotlincn.net/text/visibility-modifiers.html)
  - [扩展](https://book.kotlincn.net/text/extensions.html)
  - [数据类](https://book.kotlincn.net/text/data-classes.html)
  - [密封类](https://book.kotlincn.net/text/sealed-classes.html)
  - [泛型：in、out、where](https://book.kotlincn.net/text/generics.html)
  - [嵌套类](https://book.kotlincn.net/text/nested-classes.html)
  - [枚举类](https://book.kotlincn.net/text/enum-classes.html)
  - [内联类](https://book.kotlincn.net/text/inline-classes.html)
  - [对象表达式与对象声明](https://book.kotlincn.net/text/object-declarations.html)
  - [委托](https://book.kotlincn.net/text/delegation.html)
  - [属性委托](https://book.kotlincn.net/text/delegated-properties.html)
  - [类型别名](https://book.kotlincn.net/text/type-aliases.html)
- 函数
  - [函数](https://book.kotlincn.net/text/functions.html)
  - [lambda 表达式](https://book.kotlincn.net/text/lambdas.html)
  - [内联函数](https://book.kotlincn.net/text/inline-functions.html)
  - [操作符重载](https://book.kotlincn.net/text/operator-overloading.html)
- [类型安全的构建器](https://book.kotlincn.net/text/type-safe-builders.html)
- [空安全](https://book.kotlincn.net/text/null-safety.html)
- [相等性](https://book.kotlincn.net/text/equality.html)
- [this 表达式](https://book.kotlincn.net/text/this-expressions.html)
- [异步程序设计技术](https://book.kotlincn.net/text/async-programming.html)
- [协程](https://book.kotlincn.net/text/coroutines-overview.html)
- [注解](https://book.kotlincn.net/text/annotations.html)
- [解构声明](https://book.kotlincn.net/text/destructuring-declarations.html)
- [反射](https://book.kotlincn.net/text/reflection.html)