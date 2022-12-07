

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



#### static 方法

上文已经提到过，在 Kotlin 中没有 `static`关键字,那么如果在 Java 代码中想要通过类名调用一个 Kotlin 类的方法，你需要给这个方法加入`@JvmStatic`注解（这个注解只在 jvm 平台有用）。否则你必须通过对象调用这个方法。

```kotlin
StringUtils.isEmpty("hello");  
StringUtils.INSTANCE.isEmpty2("hello");

object StringUtils {
    @JvmStatic fun isEmpty(str: String): Boolean {
        return "" == str
    }

    fun isEmpty2(str: String): Boolean {
        return "" == str
    }
}
```

如果你阅读 Kotlin 代码，应该经常看到这样一种写法。

```kotlin
class StringUtils {
    companion object {
       fun isEmpty(str: String): Boolean {
            return "" == str
        }
    }
}
```

`companion object`表示外部类的一个伴生对象，你可以把他理解为外部类自动创建了一个对象作为自己的`field`。
与上面的类似，Java 在调用时，可以这样写：`StringUtils.Companion.isEmpty();`(1.1以后可以省略中间的 Companion，写作 `StringUtils.isEmpty())`