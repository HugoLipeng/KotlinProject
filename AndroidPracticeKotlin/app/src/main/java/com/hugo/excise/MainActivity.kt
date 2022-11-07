package com.hugo.excise

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var test = MainActivity()
        test.init()
        vars(1, 2, 3, 4, 5)


        // 1 原始代码，它的本质是用 object 关键字定义了一个匿名内部类
        imageView.setOnClickListener { v ->
            AlertDialog.Builder(this@MainActivity).apply {
                setTitle("this is Dialog")
                setMessage("Something important")
                setCancelable(false)
                setPositiveButton("OK") { dialog, which -> }
                setNegativeButton("Cancel") { dialog, which -> }
                show()
            }
            gotoPreview(v)
        }
//        // 2  object 关键字可以被省略，这时候它在语法层面就不再是匿名内部类了，它更像是 Lambda 表达式了，因此它里面 override 的方法也要跟着删掉
//        image.setOnClickListener(View.OnClickListener { v: View? -> gotoPreview(v) })
//        // 3 View.OnClickListener被称为 SAM Constructor（SAM 构造器），它是编译器为我们生成的。由于 Kotlin 的 Lambda 表达式是不需要 SAM Constructor 的，所以它也可以被删掉
//        image.setOnClickListener({ v: View? -> gotoPreview(v) })
//        // 4 由于 Kotlin 支持类型推导，所以 View 可以被删掉：
//        image.setOnClickListener({ v -> gotoPreview(v) })
//        // 5 当 Kotlin Lambda 表达式只有一个参数的时候，它可以被写成 it：
//        image.setOnClickListener({ it -> gotoPreview(it) })
//        // 6 Kotlin Lambda 的 it 是可以被省略的：
//        image.setOnClickListener({ gotoPreview(it) })
//        // 7 当 Kotlin Lambda 作为函数的最后一个参数时，Lambda 可以被挪到外面：
//        image.setOnClickListener() { gotoPreview(it) }
//        // 8 当 Kotlin 只有一个 Lambda 作为函数参数时，() 可以被省略：
//        image.setOnClickListener { gotoPreview(it) }
    }

    private fun gotoPreview(v: View?) {

    }

    private fun vars(vararg v: Int) {
        for (vt in v) {
            print(vt)
        }
    }

    private fun init() {
        name = "hugo"
    }
}


