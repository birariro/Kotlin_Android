package com.example.lottery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val clearButton: Button by lazy {
        findViewById<Button>(R.id.clearButton)
    }
    private val addButton:Button by lazy {
        findViewById(R.id.addButton)
    }
    private val runButton:Button by lazy {
        findViewById(R.id.runButton)
    }

    private val numberPicker:NumberPicker by lazy {
        findViewById(R.id.numberPicker)
    }

    private var didRun = false
    private var pickNumberSet = hashSetOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numberPicker.minValue =1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
    }
    private fun initAddButton()
    {
        addButton.setOnClickListener {
            if(didRun){
                Toast.makeText(this,"초기화후 시도해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.size >=5)
            {
                Toast.makeText(this,"번호는 5개 까지만 가능",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.contains(numberPicker.value))
            {
                Toast.makeText(this,"이미 선택한 번호입니다.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


        }
    }
    private fun initRunButton()
    {
        runButton.setOnClickListener{
            val list = getRandomNumber()

            Log.d("list",list.toString())
        }
    }
    private fun getRandomNumber():List<Int>{
        val numberList = mutableListOf<Int>()
                .apply {
                    for(i in 1..45){
                        this.add(i)
                    }
                }
        //번호를 섞는다
        numberList.shuffle()
        //번호중 앞에서 6개를 뽑는다
        val newList = numberList.subList(0,6)
        //정렬하여 반환
        return newList.sorted()
    }
}