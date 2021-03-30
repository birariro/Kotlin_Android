package com.example.lottery

import java.util.*

fun main()
{
    GetLotteryNumber2()
}


fun GetLotteryNumber()
{
    val random = Random(100)
    val numberSet = mutableSetOf<Int>()
    while(numberSet.size <6)
    {
        val randomNumber = random.nextInt(45)+1
        numberSet.add(randomNumber)
    }
    print(numberSet)

}

fun GetLotteryNumber2()
{
    //apply 는 초기화하는 구문에서 많이사용된다.
    val random = Random(100)
    val list = mutableListOf<Int>().apply {
        for(i in 1..45){
            this.add(i)
        }
    }
    //list 를 랜덤하게  섞는다.
    list.shuffle()
    //1~45까지있는 list 에서 0~6까지의 값만을 출력한다.
    print(list.subList(0,6))

}