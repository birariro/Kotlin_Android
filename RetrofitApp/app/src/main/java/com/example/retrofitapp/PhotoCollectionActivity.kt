package com.example.retrofitapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.model.Photo
import com.example.retrofitapp.recyclerview.PhotoGridRecyclerViewAdapter
import com.example.retrofitapp.utils.Constants.TAG
class PhotoCollectionActivity : AppCompatActivity() {
    //데이터
    private var photoList = ArrayList<Photo>()

    private val my_photo_recycler_view:RecyclerView by lazy {
        findViewById(R.id.my_photo_recycler)
    }
    private val top_app_bar:Toolbar by lazy {
        findViewById(R.id.top_app_bar)
    }
    //어답터
    private lateinit var photoGridRecyclerViewAdapter: PhotoGridRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)


        val bundle = intent.getBundleExtra("array_bundle")
        val searchTerm = intent.getStringExtra("search_term")
        photoList = bundle?.getSerializable("photo_array_list") as ArrayList<Photo>

        Log.d(TAG, "onCreate: searchTerm : $searchTerm  count : ${photoList.count()}")

        top_app_bar.title = searchTerm

        this.photoGridRecyclerViewAdapter = PhotoGridRecyclerViewAdapter()
        this.photoGridRecyclerViewAdapter.submitList(photoList)

        my_photo_recycler_view.layoutManager = GridLayoutManager(
                        this,
                        2,GridLayoutManager.VERTICAL,
                        false)
        my_photo_recycler_view.adapter = this.photoGridRecyclerViewAdapter

    }
}