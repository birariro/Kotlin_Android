package com.example.retrofitapp.utils

object Constants{
    const val TAG :String="로그"
}
enum class SEARCH_TYPE{
    PHOTO,USER
}

enum class RESPONSE_STATE{
    OKAY , FAIL
}

object API{
    const val BASE_URL :String ="https://api.unsplash.com/"
    const val CLIENT_ID :String ="-DFf5onmix37b0ChMS6AIzf1oHYVgzaipaUbgr4cIrc"
    const val SEARCH_PHOTO:String="search/photos"
    const val SEARCH_USERS:String="search/users"
}