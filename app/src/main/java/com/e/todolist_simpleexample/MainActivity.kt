package com.e.todolist_simpleexample

import android.app.Activity
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addBar = findViewById<LinearLayout>(R.id.addToDoLayout)
        val container = findViewById<LinearLayout>(R.id.toDoListLayout)

        for (i in 0..100){
            var tv = TextView(this)
            tv.text = "this $i"
            if (i % 2 == 0) tv.setBackgroundColor(Color.GRAY)
            container.addView(tv)
        }
    }
}
