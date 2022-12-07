

#### 注意 Class 调用

在 Java 或 Android 开发中，经常会直接调用一个类的 Class 文件。但是当你用上文介绍的转换方法去转换 `XXX.class` 这样的代码时，是无法直接转换的(也许未来会修复这个问题，但目前你扔需要手动修改)。在 M13 之前，Java 中的`XXX.class`对应 Kotlin 代码中的`JavaClass<XXX>`，而 M13 之后写法已被改为`XXX::class.java`。



