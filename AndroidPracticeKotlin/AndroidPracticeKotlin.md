# Android-第一行代码Kotlin读书笔记

[TOC]



### 第一行代码Kotlin笔记

#### 基础语法

- 优先使用val

- object实际上修饰类之后,是一个单例.并不是静态方法.

- 函数式api: any:是否存在一个满足条件,all: 是否全部都满足

- 空安全辅助: `?.` `?:` `?.let` , 其中let的这种方式,对于全局变量空安全也是适用的,而if判空则不行.

- lateinit 延迟初始化,有时候一些全局变量在使用的时候肯定是不为空的(使用之前就肯定能得到初始化),但是,每次在使用的时候依然需要进行判空处理,麻烦.直接用lateinit就行.

  

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

#### Kotlin可空的类型系统

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

##### let工具

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

##### 字符串内嵌表达式

规则如下：

"hello, my name is ${obj.name}, nice to meet you!"

表达式中只有一个变量时，可以省去大括号

"hello, my name is $name, nice to meet you!"

这样做就不需要很多加号来连接字符串了

##### 函数参数的默认值

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

首先我们给with函数的第一个参数传入了一个StringBuilder对象，那么接下来整个Lambda表达式的上下文就会是这个StringBuilder对象。于是我们在Lambda表达式中就不用再像刚才那样调用builder.append()和builder.toString()方法了，而是可以直接调用append()和toString()方法。

Lambda表达式的最后一行代码会作为with函数的返回值返回，最终我们将结果打印出来。

##### run函数

run函数和with函数非常类似，run函数会在某个对象的基础上调用，而且只接收一个Lambda参数

```
val result = StringBuilder().run {
    append("Start eating fruits:\n")
    for (fruit in list) {
        append(fruit).append("\n")
    }
    append("Ate all fruits.")
    toString()
}
```

##### apply函数

apply函数和run函数也是极其类似的，都要在某个对象上调用，并且只接收一个Lambda参数，也会在Lambda表达式中提供调用对象的上下文，但是**apply函数无法指定返回值，而是会自动返回调用对象本身**。

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

注意这里的代码变化，由于apply函数无法指定返回值，只能返回调用对象本身，因此这里的result实际上是一个StringBuilder对象，所以我们在最后打印的时候还要再调用它的toString()方法才行。

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



object

用object修饰的类,实际上是单例类,在Kotlin中调用时是类名加方法直接使用.

companion object

用companion object修饰的方法也能通过类名加.直接调用,但是这时通过伴生对象实现的.在原有类中生成一个伴生类,Kotlin会保证这个伴生类只有一个对象.

@JvmStatic注解

给单例类(object)和伴生对象的方法加@JvmStatic注解,这时编译器会将这些方法编译成真正的静态方法.

顶层方法

Kotlin会将所有的顶层方法全部编译成静态方法.

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

#### Android Studio ShortCut

注释：Ctrl+/

自动对齐：shift + alt + L

快速删除一行：ctrl + Y

删除一行并清空剪切板：ctrl + X



#### SQLite数据库操作

其实我们可以对数据进行的操作无非有4种，即CRUD。其中C代表添加（create），R代表查询（retrieve），U代表更新（update），D代表删除（delete）。每一种操作都对应了一种SQL命令，如果你比较熟悉SQL语言的话，一定会知道添加数据时使用insert，查询数据时使用select，更新数据时使用update，删除数据时使用delete。



#### 泛型和委托

##### 泛型

给方法加泛型

```kotlin
fun <T> method(param: T) : T {
    return param;
}
```

给类加泛型

```kotlin
class MyClass<T> {
    fun method(param: T) : T {
        return param;
    }
}
```

##### 委托

委托是一种设计模式,它的基本理念是: 操作对象自己不会去处理某段逻辑,而是会把工作委托给另外一个辅助对象去处理.

好处: 如果一个类,让大部分的方法实现都是调用辅助对象中的方法,而少部分的方法是自己实现的,这种情况,就是委托模式的意义所在.

类委托

kotlin支持类委托给另一个辅助对象,语法层面就支持,拥有辅助对象的所有功能,还能自己去实现或构建独有功能. 直接通过关键字by完成,简化自己去编写模板代码

```kotlin
//MySet中有所有Set接口中的功能,和HashSet保持一致, 并且isEmpty是自己实现的
class MySet<T>(val helperSet: HashSet<T>) : Set<T> by helperSet {

    fun hello() = println("hello")

    //演示,,,,
    override fun isEmpty(): Boolean {
        return true
    }

}
```

属性委托

将一个属性的具体实现委托给另一个类去完成.

#### 泛型高级特性

##### 泛型实化

Kotlin有内联函数,而内联函数是提替换到被调用的地方,所以不存在泛型擦除(泛型对于类型的约束只在编译期存在)问题.

泛型实化书写,首先得是内联函数,其次得加reified关键字,然后可以在函数内获取当前指定泛型的实际类型

```kotlin
inline fun <reified T> getGenericType() = T::class.java

val result1 = getGenericType<Int>()
val result2 = getGenericType<String>()
```

#### 协程

线程需要依靠操作系统的调度才能实现不同线程之间的切换.而协程可以在编程语言的层面实现不同协程之间的切换,从而大大提升并发编程的运行效率.

协程允许在单线程模式下模拟多线程编程的效果,代码执行时的挂起与恢复完全是由编程语言来控制,和操作系统无关.

- Kotlin for Java的协程并不属于广义的协程,而是一个线程框架
- 可以用看起来同步的代码写出实质上异步的操作
- suspend并不是拿来切线程的,而是用来做标记和提醒的,调用的时候需要放协程里面才行.
- 协程是怎么切线程的:看Kotlin编译成的class对应的Java代码发现,其实就是将代码分块,某一块代码执行在1个线程,另一块代码执行在另一个线程中.在编译的时候就搞了这样的操作.

##### 创建协程作用域

**「GlobalScope.launch」**可以创建一个协程的作用域,传递给launch函数的代码块(Lambda表达式)就是在协程中运行的.

```scss
GlobalScope.launch {
    println("codes run is coroutine scope")
    //这里不是主线程   DefaultDispatcher-worker-1
    println(Thread.currentThread().name)
}
```

GlobalScope.launch创建的是顶层协程,当应用程序结束时也会跟着结束.

可以在协程中加入delay()函数,delay()函数可以让当前协程延迟指定时间后再运行. delay是非阻塞式的挂起函数,它只会挂起当前协程.而Thread.sleep()会阻塞当前的线程,该线程的所有协程都会被阻塞.

```scss
GlobalScope.launch {
    println("codes run is coroutine scope")
    //这里不是主线程
    println(Thread.currentThread().name)

    delay(1000)
    println("延迟之后的输出")
}
```

runBlocking也能创建一个协程的作用域,它可以保证在协程作用域内的所有代码和子协程没有全部执行完之前一直阻塞当前线程.runBlocking函数通常只应该在测试环境下使用,在正式环境中容易产生性能上的问题.

##### 创建多个协程

```scss
fun main() {
    //创建多个协程
    runBlocking {
        launch {
            println("launch1 ${Thread.currentThread().name}")
            delay(1000)
            println("launch1 finished")
        }
        launch {
            println("launch2 ${Thread.currentThread().name}")
            delay(1000)
            println("launch2 finished")
        }
    }

}
```

这里的launch函数和刚才的GlobalScope.launch不一样,这个launch只能在协程的作用域下面调用,且会创建一个子协程.子协程的特点是如果外层作用域的协程结束了,那么该作用域下的所有子协程也会一同结束.

上面的输出如下:

```css
launch1 main
launch2 main
launch1 finished
launch2 finished
```

日志是交叉打印的,很明显,这是并发执行的.但是线程却是相同的线程.这里由编程语言来决定如何在多个协程之间进行调度,让谁挂起,让谁运行.调度过程不需要操作系统参与,这使得协程并发效率出奇得高.

##### suspend 挂起

当需要将部分代码提取到一个单独的函数中,这个函数是没有协程作用域的.Kotlin提供一个suspend关键字,使用它可以将任意函数声明成挂起函数,挂起函数之间是可以互相调用的.

suspend只能声明挂起函数,而不能提供协程作用域,在里面调用launch(必须在协程作用域调用才行)是不得行的.要想有协程作用域,可以使用coroutineScope.

coroutineScope也是一个挂起函数,因此可以在其他任何挂起函数中调用.coroutineScope会继承外部的协程作用域并创建一个子作用域. 于是可以这样用:

```kotlin
suspend fun printDot() {
    coroutineScope {
        launch {
            println(".")
            delay(1000)
            println("延迟之后的输出")
        }
    }
}
```

coroutineScope有点类似runBlocking,保证其作用域内的所有代码和子线程全部执行完之前,会一直阻塞当前协程. 但是runBlocking会阻塞当前线程,影响较大.而coroutineScope只会阻塞当前协程,不会影响其他协程,也不会影响其他线程.

可创建新的协程作用域:

- GlobalScope.launch 可在任何地方调用
- runBlocking 可在任何地方调用
- lanuch
- coruotineScope

##### 更多的作用域构建器

runBlocking会阻塞线程,只能在测试环境下使用.而GlobalScope.launch是顶层协程,比如在Activity中使用来请求网络,还没请求回来的时候,Activity已关闭,这时需要手动管理(去取消)这个顶层协程,比较麻烦. 调用下面的代码会取消顶层协程.

```ini
val job = GlobalScope.launch { }
job.cancel()
```

但是实际项目中,一般会用CoroutineScope

```scss
val job = Job()
//返回的是CoroutineScope对象  这里是调用的CoroutineScope方法
val scope = CoroutineScope(job)
scope.launch {

}
```

所有使用CoroutineScope对象的launch创建的协程统统会被job所管理(都是在它的作用域下面).大大降低协程维护成本.

##### 创建协程,并获取其执行结果

使用async函数,就可以获取协程的执行结果.它会创建一个子协程,并返回Deferred对象,然后我们调用其await方法即可知道结果.下面是简单计算一下5+5

```scss
runBlocking {
    val result = async {
        delay(100)
        5 + 5
    }.await()
    println(result)
}
```

注意,调用await方法之后会阻塞当前协程,直到子协程拿到结果,才会执行后面的代码(对应上面是println语句).为了提高效率,可以先拿到返回Deferred对象,最后需要结果的时候才调用await方法

```scss
runBlocking {
    val start = System.currentTimeMillis()
    val deferred1 = async {
        delay(1000)
        5 + 5
    }
    val deferred2 = async {
        delay(1000)
        6 + 6
    }
    println("结果是 ${deferred1.await() + deferred2.await()}")
    val end = System.currentTimeMillis()
    println("花费时间: ${end - start}")
}
```

##### withContext

withContext大致是async函数的简化版,它是一个挂起函数,返回结果是withContext函数体内最后一行代码.相当于val result = async{5+5}.await()

```scss
runBlocking {
    val result = withContext(Dispatchers.Default) {
        5 + 5
    }
    println(result)
}
```

调用withContext函数后,函数体内的代码会被立即执行,同时需要指定一个线程参数.这个参数有如下几个值:

- Dispatchers.Default 会开启子线程,并使用一种较低并发的线程策略.适合计算密集型任务.
- Dispatchers.IO 会开启子线程,并使用一种较高并发的线程策略.网络请求比较合适
- Dispatchers.Main 不会开启子线程,而是在Android主线程执行代码.

##### 使用协程简化回调

suspendCoroutine 函数可以将当前协程立即挂起,然后在一个普通的线程执行lambda表达式中的代码.Lambda表达式的参数是一个Continuation参数,调用它的resume方法或resumeWithException可以让协程恢复执行.

来看一段代码:

```kotlin
suspend fun request(address: String): String {
    return suspendCoroutine { continuation ->
        HttpUtil.sendHttpRequest(address, object : HttpCallbackListener {
            override fun onFinish(response: String) {
                continuation.resume(response)
            }

            override fun onError(e: Exception) {
                continuation.resumeWithException(e)
            }
        })
    }
}

GlobalScope.launch {
    val response = request("https://www.baidu.com/")
    Log.d("xfhy", "网络请求结果 : $response")
}
```

将网络请求的代码用suspendCoroutine包装一下,免得每次去手动生成一个匿名类,然后在里面拿到结果的时候调用continuation的resume方法将结果返回,这样在外面即可拿到结果. 使用request方法请求网络,只需要写一句代码即可.上面为了实例,没有加try..catch.



#### 将开源库发布到jcenter

1. 先注册一个bintray账号.网址是`https://bintray.com`
2. 然后在我的首页,点击`Add New Repository`创建一个新的仓库.仓库类型选择`Maven`,开源许可随便选,可以是`Apache-2.0`
3. 回到我们编写的库中,在项目的build.gradle中引入`bintray-release`

```arduino
buildscript {
    dependencies {
        classpath 'com.novoda:bintray-release:0.9.1'
    }
}
```

1. 然后在开源库Library的build.gradle中填写如下配置

```ini
apply plugin: 'com.novoda.bintray-release'
publish {
    userOrg = 'xfhy'//bintray.com用户名
    groupId = 'com.permissionx.xfhy'//jcenter上的路径
    artifactId = 'permissionx'//项目名称
    repoName = "permissionx"
    publishVersion = '1.0.0'//版本号
    desc = 'Make Android runtime permission request easy'//描述，不重要
    website = 'https://github.com/xfhy/PermissionX'//网站，不重要
}

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.novoda:bintray-release:0.9.1'
    }
}
```

1. 填写完成之后开始上传,在命令行输入`//上传命令: ./gradlew clean build bintrayUpload -PbintrayUser=xfhy -PbintrayKey=xxxxx -PdryRun=false` 其中PbintrayKey是Bintray的API key.
2. 命令执行完成之后,到Bintray中找到之前创建的仓库,点进去详情,点击`Add to Jcenter`,发送申请.
3. 几小时就能审核通过,然后就可以使用这个开源库了.
4. 使用方式`implementation 'com.permissionx.xfhy:permissionx:1.0.0'`





### 参考

https://blog.csdn.net/m0_56348460/category_11631624.html

https://blog.csdn.net/twistfatey/article/details/106288706/