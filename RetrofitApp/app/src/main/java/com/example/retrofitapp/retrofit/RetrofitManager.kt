package com.example.retrofitapp.retrofit

import android.util.Log
import com.example.retrofitapp.utils.API
import com.example.retrofitapp.utils.Constants.TAG
import com.example.retrofitapp.utils.RESPONSE_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import retrofit2.create

class RetrofitManager {
    //싱글 톤이 적용되도록
    companion object{
        val instance = RetrofitManager()
    }
    //레트로핏 인터페이스 가져오기
    private val iRetrofit:IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)
    //사진 검색 API 호출
    fun searchPhotos(searchTerm:String?,completion:(RESPONSE_STATE,String)->Unit){
        val term = searchTerm.let {
            it
        }?:""
        //val term =searchTerm ?: "" 와같음
        val call=iRetrofit?.searchPhotos(searchTerm = term).let {
            it
        }?:return
        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: API 성공 /response : ${response.raw()}")
                completion(RESPONSE_STATE.OKAY,response.raw().toString())
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: API 실패 /t : $t")
                completion(RESPONSE_STATE.FAIL,t.toString())
            }

        })
    }

}