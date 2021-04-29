package com.example.retrofitapp.retrofit

import android.util.Log
import com.example.retrofitapp.model.Photo
import com.example.retrofitapp.utils.API
import com.example.retrofitapp.utils.Constants.TAG
import com.example.retrofitapp.utils.RESPONSE_STATUS
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat

class RetrofitManager {
    //싱글 톤이 적용되도록
    companion object{
        val instance = RetrofitManager()
    }
    //레트로핏 인터페이스 가져오기
    private val iRetrofit:IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)
    //사진 검색 API 호출
    fun searchPhotos(searchTerm:String?,completion:(RESPONSE_STATUS, ArrayList<Photo>?)->Unit){
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
                when (response.code()) {
                    200 -> {
                        response.body()?.let {

                            var parsedPhotoDataArray = ArrayList<Photo>()
                            var body = it.asJsonObject
                            val results = body.getAsJsonArray("results")
                            val total = body.get("total").asInt
                            Log.d(TAG, "onResponse: /total : $total")
                            if(total ==0){ //데이터가 없다면
                                completion(RESPONSE_STATUS.NO_CONTENT,null)
                            }else{
                                results.forEach { resultItem ->
                                    var resultItemObject = resultItem.asJsonObject
                                    var user = resultItemObject.get("user").asJsonObject
                                    val username: String = user.get("username").asString
                                    val likesCount = resultItemObject.get("likes").asInt
                                    val thumbnailLink = resultItemObject.get("urls").asJsonObject.get("thumb").asString
                                    val createdAt = resultItemObject.get("created_at").asString
                                    val parser = SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss")
                                    val formatter = SimpleDateFormat("yyyy년\nMM월 dd일")
                                    val outputDataString = formatter.format(parser.parse(createdAt))
                                    val photoItem = Photo(author = username,
                                        likesCount = likesCount,
                                        thumbnail = thumbnailLink,
                                        createAt = outputDataString)
                                    parsedPhotoDataArray.add(photoItem)
                                }
                                completion(RESPONSE_STATUS.OKAY,parsedPhotoDataArray)
                            }

                        }

                    }
                }

            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: API 실패 /t : $t")
                completion(RESPONSE_STATUS.FAIL, null)
            }

        })
    }

}