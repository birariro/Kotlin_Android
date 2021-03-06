package com.example.kotlin_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        binding.lifecycleOwner = this; // 라이브 데이터를 사용하기 위함

        val viewModel = ViewModelProvider(this, MainViewModel.Factory(application)).get(MainViewModel::class.java)
        binding.mainViewModel =viewModel


    }
}