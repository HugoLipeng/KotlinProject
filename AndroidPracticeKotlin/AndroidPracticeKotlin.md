# Android-第一行代码Kotlin读书笔记

[TOC]



### 第一行代码Kotlin笔记

#### 基础语法

when条件语句

Kotlin中的when语句类似于Java中的switch语句，但好用的多。

when语句允许传入一个任意类型的参数，然后在结构体中定义一系列条件，格式是：

匹配值 -> {执行逻辑},当执行逻辑只有一行时，{}可以省略

```
fun getScore(name: String) : Int = when(name) {
    "Tom" -> 86
    "Jim" -> 77
    else -> 0
}
```

when语句还可以进行类型匹配

```
// Number是一个抽象类，Int、Float、Long、Double等与数字相关的类都是他的子类
fun getType(num: Number) {
	when(num) {
        is Int -> println("Int")
        is Double -> println("Double")
        else -> println("UnKnown")
    }
}

```

when语句也可以不传入参数

```
fun getScore(name: String) : Int = when {
    name == "Tom" -> 86
    name == "Jim" -> 77
    //名字以Jack开头的分数都是95
    name.startWith('Jack') -> 95
    else -> 0
}
```

注意：Kotlin中判断字符串或对象是否相等可以直接使用==关键字

#### Kotlin构造函数

和Java不同，Kotlin中有主构造函数和次构造函数的区别。

* 主构造函数：定义在类名后的构造函数，无函数体，若想在主构造函数内添加逻辑可以在init内写。默认主构造函数是无参的，但是也可以加

* 次构造函数：类内用constructor关键字定义的构造函数，次构造函数必须调用主构造函数(包括间接调用)

当一个类没有显式指定主构造函数且定义了次构造函数的时候，他就是没有主构造函数的。由于没有主构造函数，次构造函数只能直接调用父类的构造函数(this 换成 super)

```
open class Person {
    var name = ""
    var age = 0
    
    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }
}

//实例化Person类
val p = Person()
p.name = "Jack"

//现在想让Student类继承Person类，父类需要声明成open的，（默认不可继承final）
class Student : Person() // Java继承关键字是extends，而Kotlin中是冒号
{
    var sno = ""
    var grade = 0
}

//上面的Student类是默认无参的主构造函数，但是我们也可以显示地为其添加参数
//表明Student类主构造函数调用了父类Person的无参构造函数，此()不可省略
class Student(val sno: String, val grade: Int): Person() {
    // 主构造函数的逻辑可以写在init内
    init {
        println("sno is " + sno)
        println("grade is " + grade)
    }
    
}

// 现在的问题是，子类的构造函数必须调用父类的构造函数，但是Kotlin中主构造函数并没有函数体，我们有时也不会写init，所以为了能够
// 知道子类主构造函数调用的是父类的哪个构造函数，所以在后面加个括号显式地指定。

// 主构造函数中声明成val 或者 var 的参数会自动成为该类的字段
```

#### 接口

Kotlin和Java一样是单继承语言，实现多态需要有接口。一个类最多只能继承一个父类，但是可以实现多个接口。但是可以实现多个接口。

接口中的函数不要求有函数体

#### 单例类

java单例模式

```
    public class Singleton{

        private static Singleton instance;

        private Singleton(){}

        public synchronized static Singleton getInstance(){
            if (instance =null) {
                instance new Singleton();
            }
                return instance;
        }
        
        public void singletonTest(){
            System.out.println("singletonTest is called.");
        }

    }
```

这段代码其实很好理解，首先为了禁止外部创建Singletoni的实例，我们需要用private关键字将

Singleton的构造函数私有化，然后给外部提供了一个getInstance()静态方法用于获取Singleton的实例。在getInstance()方法中，我们判断如果当前缓存的Singleton实例为null，就创建一个新的实例，

否则直接返回缓存的实例即可，这就是单例模式的工作机制。

而如果我们想调用单例类中的方法，也很简单，比如想调用上述的singletonTest()方法，就可以这样写：

```
Singleton singleton Singleton.getInstance(); 
singleton.singletonTest();
```

在Kotlin中创建一个单例类的方式极其简单，只需要将class关键字改成object关键字即可。现在我们尝试创建一个Kotlin版的Singleton单例类，右击com.example.helloworld包→New→Kotlin File/Class，在弹出的对话框中输入“Singleton”，创建类型选择“Object”，点击“OK”完成创建，初始代码如下所示：

```
object Singleton{
		fun singletonTest(){
				println("singletonTest is called.") 
		}
}

Singleton.singletonTest()
```



#### Lambda编程

list集合：

listOf：不可变的集合

mutableListOf:可变的集合

for-in循环可以对其遍历

set集合，用法与list集合基本一致。

setOf/ mutableSetOf

map集合

```
fun main() {
    var list = mutableListOf("Apple", "Banana", "Orange", "Grape") //listOf 不可变的集合
    list.add("Watermelon")
//    for (fruit in list)
//        println(fruit)

//    var map = HashMap<String, Int>()
//    map.put("Apple", 1)
//    map.put("Pear", 2)
//    map.put("Orange", 3)

//    map["Apple"] = 1
//    map["Pear"] = 2
//    map["Orange"] = 3

    //这里的to不是关键字而是一个infix函数
    var map = mapOf<String, Int>("Apple" to 1, "Pear" to 2, "Orange" to 3)
    for ((fruit, number) in map)
        println("$number: $fruit")
}
```

集合的函数式API
当Lambda表达式的参数列表只有一个参数时，也不必声明参数名，直接使用it代替

maxBy()

map()

调用Java函数式API
Kotlin代码调用Java方法时也可以使用函数式API，不过有限制：Java方法接收一个Java单抽象方法接口参数（接口中只有一个待实现方法）

kotlin中舍弃了new关键字，创建匿名对象用object关键字

Kotlin可空的类型系统
Kotlin为了解决空指针异常的问题，会再编译时进行判空检查，即所有变量和参数都不可为空，否则编译不通过。而当我们想要某个变量就是为空时，可以采用可为空的类型系统，即在类名后再加一个问号，但还是得将潜在的空指针异常都给处理掉，否则编译无法通过。

```
fun doStudy(study: Study?) {
	study?.readBooks()
    study?.doHomework()
    //上述代码等效于
    if (study != null)
    {
        study.readBooks()
        study.doHomework()
    }
}
```

?:操作符：左右两边各接受一个表达式，如果左边结果不为空就返回左边的结果，否则返回右边的结果

```
val c = if (a != null) {
    a
} else {
    b
}
// 用?:运算符简化为
val c = a ?: b
```

有时我们从逻辑上进行了判空的处理，但是Kotlin编译器并不知道，还是会编译失败，这个时候可以使用非空断言工具（!!），强制通过编译（允许抛异常）

```
fun printUpperCase() {
    val upperCase = content!!.toUpperCase() //非空断言
    println(upperCase)
}
```

let工具
let是一个函数，提供了函数式API的编程接口，并将原始调用对象作为参数传递到Lambda表达式中：

```
obj.let { obj2 ->
    // 具体的操作
}
```

这里的obj2其实是obj对象的别名，调用obj的let函数后，Lambda表达式中的代码会立即执行。

这里给出一个使用let的示例:

```
fun doStudy(study: Study?) {
	study?.readBooks()
    study?.doHomework()
    //上面的代码虽然可以编译通过，但其逻辑实际上有点啰嗦，等价于：
    if (study != null)
    	study.readBooks()
    if (study != null)
    	study.doHomework()
    //可以利用let简化为：
    study?.let { stu ->
        stu.readBooks()
        stu.doHomework()
    }
    //当lambda表达式的参数列表只有一个参数时，可以不声明参数，直接用it关键字来代替
    study?.let {
        it.readBooks()
        it.doHomework()
    }
}
```

值得注意的是，即使在函数内进行了判空处理，但全局变量仍然可能会被其他线程修改，用if判断不能保证安全，但let可以（相当于上锁了？）。

这里说的空是指null，而不是说空字符串

#### Kotlin中的小魔术

介绍一下小技巧

字符串内嵌表达式
规则如下：

"hello, my name is ${obj.name}, nice to meet you!"

表达式中只有一个变量时，可以省去大括号

"hello, my name is $name, nice to meet you!"

这样做就不需要很多加号来连接字符串了

函数参数的默认值
规则如下：

```
fun printParams(num: Int, str:String = "hello") {
    println("num is $num, str is $str")
}
```

这个时候我们想让第一个参数有默认值，而第二个参数传入值，按照上面的写法肯定是不行的，编译器会把字符串传给第一个参数，实际上，诸如在C++中，会要求把具有默认值的参数放在后面。但Kotlin可以通过键值对的方式来传参，可以解决上述类型不匹配的错误。

例如：

printParams(str = "world", num = 123)

因此我们可以用这种方法来解决上述需求：

```
fun printParams(num: Int = 1024, str:String) {
    println("num is $num, str is $str")
}
printParams(str = "world")
```

正因为函数参数默认值和键值对传参的机制，Kotlin中可以在主构造函数中指定参数默认值从而代替次构造函数。

#### chap3 Kotlin标准函数和静态方法

##### 标准函数

Kotlin的标准函数指的是Standard.kt文件中定义的函数。

##### with函数

现在有如下程序，利用StringBuilder构造吃水果的字符串，然后打印出来

```
val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
val builder = StringBuilder()
builder.append("Start eating fruits:\n")
for (fruit in list) {
    builder.append(fruit).append("\n")
}
builder.append("Ate all fruits.")
val result = builder.toString()
println(result)
```

可以看见，在每次for循环时我们都调用了builder对象的append方法，这个时候我们可以考虑用with函数让代码更精简。

Kotlin规定：当Lambda表达式是函数的最后一个参数时，可以将Lambda表达式移到函数的外边去。

```
// 本质上是with(param1, {lambda表达式}),此时lambda表达式可以提到外边去
val result = with(StringBuilder()) {
    append("Start eating fruits:\n")
    for (fruit in list) {
        append(fruit).append("\n")
    }
    append("Ate all fruits.")
    toString()
}
```

我们传给with表达式的第一个参数作为Lambda表达式的上下文。所以可以直接调用append。

最后一行代码作为with函数的返回值返回。

##### run函数

run函数和with函数非常类似，run函数会在某个对象的基础上调用，而且只接收一个Lambda参数

```
val result = StringBuilder();
result.run {
    append("Start eating fruits:\n")
    for (fruit in list) {
        append(fruit).append("\n")
    }
    append("Ate all fruits.")
    toString()
}
```

##### apply函数

apply函数和run函数很相似，都在某个对象的基础上调用，只接收一个Lambda参数，但apply函数无法指定返回值。

```
val result = StringBuilder();
result.apply {
    append("Start eating fruits:\n")
    for (fruit in list) {
        append(fruit).append("\n")
    }
    append("Ate all fruits.")
}
println(result.toString())
```

##### 静态方法

静态方法是指无需创建实例就能调用的方法。Java中加上Static关键字就可以了

静态方法非常适合编写工具类的一些功能，因为工具类通常没有创建实例的必要。

Kotlin中弱化了静态方法这个概念，因为有单例类object作为代替

但是单例类会将类内所有方法全部变成类似静态方法的调用，如果只想要类内的某一个方法变为静态方法，可以使用companion object

```
class Util {
    fun doAction1() {
        
    }
  //doAction2方法可以直接使用Util.doAction2()的方式调用  
    companion object {
        fun doAction2() {

        }
    }
}
```

但其实严格上，doAction2也不是静态方法，使用companion object关键字定义的方法，会在Util类内创建一个伴生类，保证这个伴生类只有一个实例，然后再调用这个伴生类实例的方法。

##### 定义真正的静态方法

Kotlin提供了两种实现方式：注解和顶层方法

注解

```
companion object {
    
    @JvmStatic
    fun doAction2(
    }
}
```

注意：注解@JvmStatic只能加在单例类或者companion object中的方法上。

顶层方法

顶层方法指的是没有定义在任何类中的方法。Kotlin编译器会将所有的顶层方法编译成静态方法。在Kotlin中调用的时候直接用函数名即可。

但是在Java中，任何函数都应该定义在类内，比如我们在Helper.kt中定义了一个顶层方法doSomething()，在Java中可以使用HelperKt.doSomething()的方式调用。

#### chap4 延迟初始化和密封类

对变量延迟初始化
Kotlin中变量不可为空减少了程序的Bug，但有时用起来不方便，每次都需要判空处理，，即使你确定他们不为空。

```
class MainActivity : AppCompatActivity() {

    private val msgList = ArrayList<Msg>()
    private var adapter: MsgAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //初始化消息列表
        initMsg()
        //加载布局管理器
        val layoutManager = LinearLayoutManager(this)
        recycleView.layoutManager = layoutManager
        //适配器
        adapter = MsgAdapter(msgList)
        recycleView.adapter = adapter
        //为Button添加响应
        send.setOnClickListener {
            val content = inputText.text.toString()
            if (content != null) {
                val msg = Msg(content, Msg.TYPE_SEND)
                msgList.add(msg)
                //刷新RecycleView中的显示
                adapter?.notifyItemInserted(msgList.size - 1)
                //定位到最后一行
                recycleView.scrollToPosition(msgList.size - 1)
                //清空输入框
                inputText.setText("")
            }
        }
    }
}
```

上例中，我们在onCreate()方法内对adapter进行初始化，但由于将其设置为全局变量，所以不得不将其赋为null，这个问题的解决办法是对全局变量进行延迟初始化，关键字是lateinit,它告诉Kotlin编译器，我会晚些时候对变量初始化，这样就不用一开始就赋给null。

优化代码如下：

```
class MainActivity : AppCompatActivity() {

    private val msgList = ArrayList<Msg>()
//    private var adapter: MsgAdapter? = null
    // 变量延迟初始化
    private lateinit var  adapter:MsgAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
				...
                adapter.notifyItemInserted(msgList.size - 1)
				...
        }
    }
}
```

使用lateinit关键字的时候，一定要保证它在任何地方被调用之前已经完成了初始化工作。

此外，我们还可以通过代码判断一个全局变量是否已经初始化：

```
if (!::adapter.isInitialized) {
    adapter = MsgAdapter(msgList)
}
```


::adapter.isInitialized用于判断adapter变量是否已经初始化。

密封类
来看一个示例：

```
interface Result
class Success(val msg: String) : Result
class Failure(val error: Exception) : Result

fun getResultMsg(result: Result) = when(result) {
    is Success -> result.msg
    is Failure -> result.error
    else -> throw IllegalArgumentException()
}
```

这里定义了一个接口Result，然后getResultMsg会根据结果类别返回不同结果，然而这里需要有一个else的分支。但else分支并不是很安全，原因是这个else分支永远也走不到，但Kotlin编译器又要求要有这个分支。此外还有一个额外的风险，即当我们新增一个分支时，但忘记在函数体内写这一分支，会默认进入else分支内抛出异常。

Kotlin的密封类可以很好的解决这个问题，关键字是sealed class，下面将接口Result改造成密封类：

```
sealed class Result
class Success(val msg: String) : Result()
class Failure(val error: Exception) : Result()

fun getResultMsg(result: Result) = when(result) {
    is Success -> result.msg
    is Failure -> result.error.message
}
```

和interface的区别在于，密封类是一个类，继承时需要加括号(构造函数)，这时在when语句内我们可以不写else分支，Kotlin会检查密封类有哪些子类，并强制要求你将所有子类对应的分支都处理。密封类及其子类只能在定义在同一个文件的顶层位置，不能定义成内部类。

#### chap5 高阶函数详解

##### 定义高阶函数

如果一个函数接收另一个函数作为参数，或者返回值类型是函数，则该函数称为高阶函数。

函数类型的基本规则：

```
//String, Int是接收的两个参数
//返回值是Unit类型，相当于Java里的void
(String, Int) -> Unit
```

再看一个高阶函数的实例：

```
// 高阶函数
fun example(str: String, i: Int, func: (String, Int) -> Unit) {
    func(str, i)
}

// 打印字符串n次的普通函数
fun repeatStringNTimes(str:String, i: Int) {
    repeat(i) {
        println(str)
    }
}

fun main() {
    example("hello", 3, ::repeatStringNTimes)
}
```

注意，函数作为参数传递的时候写法是::funcName

但这种写法也有点复杂，每次都要定义一个匹配的普通函数，Kotlin还支持其他方式调用高阶函数，如Lambda表达式、匿名函数、成员引用等。上例用Lambda表达式写法是：

```
fun example(str: String, i: Int, func: (String, Int) -> Unit) {
    func(str, i)
}

fun main() {
    example("hello", 3){str, i ->
        repeat(i) { println(str)}
    }
}
```

还是很方便的。

```
fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}

fun main() {
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().build {
        append("Start eating fruits\n")
        for (fruit in list) {
            append("eating $fruit\n")
        }
        append("End eating fruits!")
    }
    println(result.toString())
}
```

这里我们给StringBuilder类定义了一个build扩展函数，接受一个函数类型参数，返回值也是StringBuilder类。

这里的StringBuilder.的语法结构，加在了函数类型() -> Unit前面，表示这个函数类型是定义在哪个类当中的，这样做可以使得调用build函数传入的Lambda表达式会自动拥有StringBuilder的上下文，其实这也是apply函数的实现方式。

理解与疑惑：

这里build函数传入了一个函数类型的参数，这个函数类型是() -> Unit，所以Lambda表达式中也没有参数，但最后一行会作为Lambda表达式的返回值，查询StringBuilder.append()函数返回值类型是StringBuilder而非Unit，所以很疑惑这里为啥给Unit返回类型也可以，经过尝试将Unit改成StringBuilder可以得出一样的结果，这种是说得通的。、

##### 内联函数的作用

kotlin的高阶函数、Lambda底层的实现方式是匿名类，因此会有创建和调用匿名类的开销，为了消除运行时的额外开销，可以将高阶函数声明成内联函数。关键字是inline:

```
inline fun num1Andnum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}
```

内联函数的工作原理是将内联函数中的代码在编译的时候自动替换到调用它的地方。可以消除调用的开销。

但如果给函数加上inline关键字，则Kotlin编译器会自动将所有引用的Lambda表达式全部内联。如果我们只想内联其中一个表达式，可以使用noinline关键字：

```
inline fun inlineTest(block1: ()-> Unit, noinline block2: () -> Unit) {
}
```

为何要加入noinline关键字：内联的函数类型参数没有真正的参数属性，非内联的函数类型参数就是一个参数，可以自由地传给其他任何函数，而内联的函数类型参数只能传给内联函数。

```
fun printString(str: String, block1: (str: String) -> Unit) {
    println("printString start")
    block1(str)
    println("printString end")
}

fun main() {
    println("main start")
    val str = ""
    printString(str) {s ->
        println("lambda start")
        if (s.isEmpty()) return@printString
        println(s)
        println("lambda end")
    }
    println("main end")
}
```

非内联函数只能局部返回。Lambda表达式内不允许直接使用return关键字，这里return@printString是局部返回，而如果我们声明成内联函数的形式，就可以使用return关键字返回（当然也可以使用局部返回），这时return就直接是主函数的返回了，因为inline是直接在main函数内展开。

```
inline fun printString(str: String, block1: (str: String) -> Unit) {
    println("printString start")
    block1(str)
    println("printString end")
}

fun main() {
    println("main start")
    val str = ""
    printString(str) {s ->
        println("lambda start")
        if (s.isEmpty()) return
        println(s)
        println("lambda end")
    }
    println("main end")
}
```

将高阶函数声明成内联函数是一个好的编程习惯，绝大多数高阶函数都可以声明成内联函数的，但也有例外。

```
inline fun runRunnable(block: () -> Unit) {
    val runnable = Runnable {
        block()
    }
    runnable.run()
}
```

会提示：Can’t inline ‘block’ here: it may contain non-local returns. Add ‘crossinline’ modifier to parameter declaration ‘block’。

原因在于：我们在内联函数内部创建了一个匿名对象，而这个匿名对象调用了函数参数类型，由于函数声明为内联，所以Lambda内部可以使用return返回，但一旦有return，则匿名类的函数一定返回不了。这就导致了冲突。

即：如果我们在高阶函数中创建了另外的Lambda表达式或者匿名类的实现，并且在这些实现中调用了函数类型参数，且高阶函数声明成inline，则一定会提示错误。

解决办法是加上crossinline关键字，其作用是告诉编译器内联函数内的Lambda一定不会使用return（但还是可以使用局部返回的），这样就解决了冲突。

```
inline fun runRunnable(crossinline block: () -> Unit) {
    val runnable = Runnable {
        block()
    }
    runnable.run()
}
```

Android Studio ShortCut
注释：Ctrl+/

自动对齐：shift + alt + L

快速删除一行：ctrl + Y

删除一行并清空剪切板：ctrl + X






### 参考

https://blog.csdn.net/m0_56348460/category_11631624.html