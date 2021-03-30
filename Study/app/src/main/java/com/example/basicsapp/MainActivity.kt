package com.example.basicsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText1 = findViewById<EditText>(R.id.editText1)
        val editText2 :EditText = findViewById(R.id.editText1)
        val button :Button = findViewById(R.id.button)

        button.setOnClickListener {
            if(editText1.text.isEmpty() || editText2.text.isEmpty())
            {
                //return 을하면  onCreate를 나가는건지 setOnClickListener 를 나가는건지 모르기때문에 명시한다.
                return@setOnClickListener
            }
            Log.d("key","editText1 : ${editText1.text} , ${editText2.text}")
        }
    }
}