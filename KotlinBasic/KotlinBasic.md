# KotlinBasic

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