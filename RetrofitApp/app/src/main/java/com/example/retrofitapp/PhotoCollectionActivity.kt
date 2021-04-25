package com.example.retrofitapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitapp.model.Photo
import com.example.retrofitapp.utils.Constants.TAG
class PhotoCollectionActivity : AppCompatActivity() {

    private var photoList = ArrayList<Photo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)


        val bundle = intent.getBundleExtra("array_bundle")
        val searchTerm = intent.getStringExtra("search_term")
        photoList = bundle?.getSerializable("photo_array_list") as ArrayList<Photo>
        Log.d(TAG, "onCreate: searchTerm : $searchTerm  count : ${photoList.count()}")
    }
}