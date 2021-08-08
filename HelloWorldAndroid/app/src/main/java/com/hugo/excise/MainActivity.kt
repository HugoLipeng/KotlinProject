package com.hugo.excise

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

class MainActivity : AppCompatActivity() {
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var test = MainActivity()
        test.init()
        vars(1, 2, 3, 4, 5)
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
