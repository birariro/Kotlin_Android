package com.example.coroutineapp

import kotlinx.coroutines.*

fun main(){
    runBlocking {
        val a = launch {
            for (i in 1..5){
                println(i)
                delay(10)
            }
        }
        val b = async {
            for (i in 1..5){
                println(i)
                delay(10)
            }
            "async 종료"
        }
        println("async 대기")
        println(b.await())

        println("launch 취소")
        a.join()
        print("launch 종료")
    }
}
