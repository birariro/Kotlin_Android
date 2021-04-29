package com.example.retrofitapp.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapp.App
import com.example.retrofitapp.R
import com.example.retrofitapp.model.Photo

class PhotoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    //뷰들을 가져온다 .
    private val photoImageView = itemView.findViewById<ImageView>(R.id.photo_image)
    private val photoCreatedAtText = itemView.findViewById<TextView>(R.id.created_at_text)
    private val photoLikesCountText = itemView.findViewById<TextView>(R.id.likes_count_text)


    //데이터와 뷰를 묶는다
    fun bindWithView(photoItem:Photo){
        photoCreatedAtText.text = photoItem.createAt
        photoLikesCountText.text=photoItem.likesCount.toString()
        // 글라이드를 사용하여 이미지를 설정한다.
        Glide.with(App.instance)
                .load(photoItem.thumbnail)
                .placeholder(R.drawable.ic_baseline_insert_photo_24)//위의 이미지가 비정상일때
                .into(photoImageView) //일로 보여줘라
    }

}