package com.example.navermap

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/84e464ae-580c-4f00-90fc-2f6b9fc18319")
    fun getHouseList(): Call<HouseDto>
}