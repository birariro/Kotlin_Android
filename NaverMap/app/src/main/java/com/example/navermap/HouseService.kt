package com.example.navermap

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/e952f3c5-66ba-4066-a43e-44a44387347a")
    fun getHouseList(): Call<HouseDto>
}