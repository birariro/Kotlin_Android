package com.example.photoview

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.timer

class PhotoFrameActivity :AppCompatActivity(){
    private val photoList = mutableListOf<Uri>()
    private var currentPosition=0
    private val photoImageView:ImageView by lazy {
        findViewById(R.id.photoImageView)
    }
    private val backgroundPhotoImageView:ImageView by lazy {
        findViewById(R.id.backgroundPhotoImageView)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photoframe)
        getPhotoUriFromIntent()
        startTimer()
    }

    private fun getPhotoUriFromIntent(){
        val size = intent.getIntExtra("photoListSize",0)
        for(i in 0..size){
            intent.getStringExtra("photo$i")?.let {
                photoList.add(Uri.parse(it))
            }
        }
    }
    private fun startTimer(){
        //5초에 한번씩 실행
        //메인스레드가 아니다.
        timer(period = 5*1000){
            //메인스레드로 동작
            runOnUiThread{
                val current = currentPosition
                //마지막 이미지이면 다시 처음부터 동작 하도록 index 를 초기화한다.
                val next =if(photoList.size <= currentPosition +1) 0 else currentPosition+1
                backgroundPhotoImageView.setImageURI(photoList[current])
                //투명도
                photoImageView.alpha = 0f //안보이게
                photoImageView.setImageURI(photoList[next])
                photoImageView.animate()
                        .alpha(1.0f)
                        .setDuration(1000)
                        .start()
                currentPosition = next
            }
        }
    }
}