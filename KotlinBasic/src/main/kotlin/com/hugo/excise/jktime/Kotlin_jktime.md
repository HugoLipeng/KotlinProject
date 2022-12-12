[TOC]



### Kotlin与Java的一些交互

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



### 扩展函数

Kotlin扩展函数能够对⼀个类扩展新功能⽽⽆需继承该类或者使⽤像装饰者这样的设计模式。利用他可以减少很多代码，提升我们的开发效率。

#### 扩展函数的声明

声明⼀个函数，然后让被扩展的类型作为函数的前缀就可以了，比如下面对TextView的扩展

```kotlin
fun TextView.setDrawableLeft(drawableId: Int?) {
    val drawable = ContextCompat.getDrawable(this.context, drawableId)
    if (drawable == null) {
        setCompoundDrawables(
            null,
            compoundDrawables[1],
            compoundDrawables[2],
            compoundDrawables[3]
        )
    } else {
        drawable.apply {
            setBounds(0, 0, minimumWidth, minimumHeight)
            setCompoundDrawables(
                drawable,
                compoundDrawables[1],
                compoundDrawables[2],
                compoundDrawables[3]
            )
        }
    }
}
```

然后我们就可以使用TextView的实例调用setDrawableLeft()方法。虽然看上去好像我们为TextView新增了一个方法，但是其实扩展是静态的，他并没有为扩展类型中插入新的方法

那么他是如何实现的呢，我们反编译以下class文件，查看对应的java文件就明白了

#### 扩展函数原理

```less
public static final void setDrawableLeft(@NotNull TextView $this$setDrawableLeft, @Nullable Integer drawableId)
{
    //省略其他代码
}
```

原来是给我们生成了一个对应的java静态方法，第一个参数就是接受者类型的对象，所以在方法内部可以访问这个类中的成员。

既然是生成了java代码，那么这个方法就可以被其他java代码访问

```reasonml
TextViewKt.setDrawableLeft(textview,drawableId)
```

### 扩展属性

和扩展函数类似，这种扩展也是静态的，并没有给原来的类添加新的属性

#### 扩展属性的声明

```kotlin
val StringBuilder.lastChar: String
    get() {
        if (this.isEmpty()) {
            return ""
        }
        return this.substring(length - 1)
    }
```

声明方式和扩展函数也类似，需要一个接受者类型，在作用域类，this就代表这个接受者对象，由于这里lastChar我们定义的是val，所以只用定义get()方法就可以了，如果是var类型，还需要定义set()方法，扩展字段的使用和正常字段一样。

```isbl
val s = StringBuilder("value")
println(s.lastChar)
```

#### 扩展属性原理

继续反编译查看对应的java源码

```kotlin
public static final String getLastChar(@NotNull StringBuilder $this$lastChar)
  {
    Intrinsics.checkNotNullParameter($this$lastChar, "<this>");
    if ((((CharSequence)$this$lastChar).length() == 0 ? 1 : 0) != 0) {
      return "";
    }
    String str = $this$lastChar.substring($this$lastChar.length() - 1);Intrinsics.checkNotNullExpressionValue(str, "this.substring(length - 1)");return str;
  }
```

可以看到其实也是生成了一个静态方法，java代码调用如下

```reasonml
StringBuilder s = new StringBuilder("value");
String lastChar = EXTKt.getLastChar(s);
```

参考：https://segmentfault.com/a/1190000041713663