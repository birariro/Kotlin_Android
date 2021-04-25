package com.example.retrofitapp.model

import java.io.Serializable

data class Photo(var thumbnail :String? ,
                 var author : String? ,
                 var createAt:String? ,
                 var likesCount:Int?) :Serializable{
}