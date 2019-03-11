package com.e.todolist_simpleexample

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private val toDoList: ArrayList<String> = ArrayList()
    private lateinit var toDoAdapter: ArrayAdapter<String>
    private lateinit var listV: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listV = findViewById<ListView>(R.id.listView)

        toDoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, toDoList)
        listV.adapter = toDoAdapter
        toDoList.add("first task")
        toDoList.add("second task")

        /* for textview
        for (i in 0..100){
            var tv = TextView(this)
            tv.text = "this $i"
            if (i % 2 == 0) tv.setBackgroundColor(Color.GRAY)
            container.addView(tv)
        }
        */
    }

    //todo remove ToDoItem

    fun addToDo(view: View) {
        val toDoText = findViewById<EditText>(R.id.toDoEditText)
        val text = toDoText.text.toString()
        toDoAdapter.add(text)
        toDoText.setText("")
    }
}
