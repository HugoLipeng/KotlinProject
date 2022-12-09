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