package com.example.retrofitapp.retrofit

import android.util.Log
import com.example.retrofitapp.utils.Constants.TAG
import retrofit2.Retrofit

//코틀린에서 오브젝트는 싱글톤 이다.
object RetrofitClient {
    //레트로핏 클라이언트 선언
    private lateinit var retrofitClient: Retrofit

    //레트로핏 클라이언트 가져오기
    fun getClient(baseUrl:String):Retrofit{
        Log.d(TAG, "getClient: ")
        if(retrofitClient == null){
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient
    }
}