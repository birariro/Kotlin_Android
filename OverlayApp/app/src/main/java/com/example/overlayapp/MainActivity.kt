package com.example.overlayapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private val serviceIntent by lazy {  Intent(this, ImmortalService::class.java)  }
    companion object {
        private const val ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 1004
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermission() {

        when{
            Settings.canDrawOverlays(this) -> {
                callService()
            }
            else->{
                permissionPopup()
            }
        }

    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun permissionPopup(){
        AlertDialog.Builder(this@MainActivity)
                .setTitle("권한이 필요합니다.")
                .setMessage("화면위의 그리기 권한이 필요")
                .setPositiveButton("확인") { _, _ ->
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                        Uri.parse("package:$packageName"))
                    startActivityForResult(intent,
                                        ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE)
                }
                .setNegativeButton("취소") { _, _ -> }
                .create().show()

    }
    private fun callService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            callService()
        }
    }
}