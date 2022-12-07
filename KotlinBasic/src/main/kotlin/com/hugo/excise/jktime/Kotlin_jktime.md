

#### 注意 Class 调用

在 Java 或 Android 开发中，经常会直接调用一个类的 Class 文件。但是当你用上文介绍的转换方法去转换 `XXX.class` 这样的代码时，是无法直接转换的(也许未来会修复这个问题，但目前你扔需要手动修改)。在 M13 之前，Java 中的`XXX.class`对应 Kotlin 代码中的`JavaClass<XXX>`，而 M13 之后写法已被改为`XXX::class.java`。



#### 与 Kotlin 关键字冲突的处理

Java 有 static 关键字，在 Kotlin 中没有这个关键字，你需要使用`@JvmStatic`替代这个关键字。
同样，在 Kotlin 中也有很多的关键字是 Java 中是没有的。例如 `in`,`is`,`data`等。如果 Java 中使用了这些关键字，需要加上反引号(`)转义来避免冲突。例如

```kotlin
// Java 代码中有个方法叫 is()
public void is(){
    //...
}

// 转换为 Kotlin 代码需要加反引号转义
fun `is`() {
   //...
}
```



