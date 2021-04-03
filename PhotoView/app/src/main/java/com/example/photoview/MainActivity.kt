package com.example.photoview

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    val startPhotoFrameBodeButton: Button by lazy {
        findViewById(R.id.startPhotoFrameBodeButton)
    }
    val addPhotoButton:Button by lazy {
        findViewById(R.id.addPhotoButton)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAddPhotoButton()
        initStartPhotoFrameBodeButton()
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initAddPhotoButton(){
        addPhotoButton.setOnClickListener {
            when{
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED ->{
                    //권한이 잘 부여가 되었을때
                }
                //교육용
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)->{
                    // todo 교육용 팝업확인후 권한 팝업
                    showPermissionContextPopup()
                }
                else -> {
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1000)
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun showPermissionContextPopup(){
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("전자액자에서 앱에서 사진을 불러오기위해 권한이 필요합니다.")
            .setPositiveButton("동의하기") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }
            .setNegativeButton("취소하기") { _, _ -> }
            .create().show()
    }
    private fun initStartPhotoFrameBodeButton(){

    }
}