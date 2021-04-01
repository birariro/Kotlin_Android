package com.example.lottery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

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
    private val numberTexViewList:List<TextView> by lazy {
        listOf<TextView>(
                findViewById(R.id.textView1),
                findViewById(R.id.textView2),
                findViewById(R.id.textView3),
                findViewById(R.id.textView4),
                findViewById(R.id.textView5),
                findViewById(R.id.textView6)
        )
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
        initClearButton()
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
            val textView = numberTexViewList[pickNumberSet.size]
            textView.isVisible=true
            textView.text = numberPicker.value.toString()

            setNumberBackground(numberPicker.value,textView)

            pickNumberSet.add(numberPicker.value)

        }
    }
    private fun initClearButton()
    {
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTexViewList.forEach {
                it.isVisible=false

            }
            didRun =false
        }
    }
    private fun initRunButton()
    {
        runButton.setOnClickListener{
            val list = getRandomNumber()
            didRun =true
            list.forEachIndexed{ index , number->
                val textView = numberTexViewList[index]
                textView.text = number.toString()
                textView.isVisible =true
                setNumberBackground(number,textView)
            }
            Log.d("list",list.toString())
        }
    }
    private fun setNumberBackground(number:Int, textView: TextView)
    {
        when(number){
            in 1..10 ->  textView.background = ContextCompat.getDrawable(this,R.drawable.circle_yello)
            in 11..20 ->  textView.background = ContextCompat.getDrawable(this,R.drawable.circle_blue)
            in 21..30 ->  textView.background = ContextCompat.getDrawable(this,R.drawable.circle_red)
            in 31..40 ->  textView.background = ContextCompat.getDrawable(this,R.drawable.circle_gray)
            else ->  textView.background = ContextCompat.getDrawable(this,R.drawable.circle_green)
        }
    }
    private fun getRandomNumber():List<Int>{
        val numberList = mutableListOf<Int>()
                .apply {
                    for(i in 1..45){
                        if(pickNumberSet.contains(i)) {
                            continue
                        }
                        this.add(i)
                    }
                }
        //번호를 섞는다
        numberList.shuffle()
        // 랜덤값을 추가할때 기존의 번호리스트 에다가 추가한다.
        val newList = pickNumberSet.toList()+ numberList.subList(0,  6 - pickNumberSet.size)
        //정렬하여 반환
        return newList.sorted()
    }
}