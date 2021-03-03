package com.example.kotlin_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val todo_edit:EditText = findViewById(R.id.todo_edit)
        val add_button:Button = findViewById(R.id.add_button)
        val resultText:TextView = findViewById(R.id.resultText)
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "todo-db")
                .build()

            db.TodoDAO().GetAll().observe(this, Observer {
                resultText.text=it.toString();
            })

           add_button.setOnClickListener{
               lifecycleScope.launch(Dispatchers.IO) {
                   db.TodoDAO().Insert(Todo(todo_edit.text.toString()))
               }

           }


    }
}