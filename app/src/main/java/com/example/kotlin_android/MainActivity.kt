package com.example.kotlin_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.kotlin_android.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.lifecycleOwner = this; // 라이브 데이터를 사용하기 위함

        val todo_edit:EditText = findViewById(R.id.todo_edit)
        val add_button:Button = findViewById(R.id.add_button)
        val resultText:TextView = findViewById(R.id.resultText)

        val viewModel = ViewModelProvider(this, MainViewModel.Factory(application)).get(MainViewModel::class.java)
        binding.mainActivityViewModel =viewModel

        viewModel.getAll().observe(this, {
                resultText.text=it.toString();
            })

       add_button.setOnClickListener{
           lifecycleScope.launch(Dispatchers.IO) {
               viewModel.insert(Todo(todo_edit.text.toString()))
           }

       }
    }
}