package com.example.overlayapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.WindowManager
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import java.util.zip.Inflater


class ImmortalService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelID = "채널id"
            val strTitle = "타이틀"

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            var channel = notificationManager.getNotificationChannel(channelID)
            var builder: NotificationCompat.Builder? = null
            var notification: Notification? = null
            if (channel == null) {
                channel = NotificationChannel(
                    channelID,
                    strTitle,
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationManager.createNotificationChannel(channel)
            }
            builder = NotificationCompat.Builder(this, channelID)
            builder.setSmallIcon(R.mipmap.ic_launcher)
            builder.setContentText("노티 텍스트")
            notification = builder.build()
            notificationManager.notify(1, notification)
            startForeground(1, notification)
        }
        initView()
    }

    fun initView(){
        // inflater 를 사용하여 layout 을 가져오자
        val inflate = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // 윈도우매니저 설정
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            else WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,  // Android O 이상인 경우 TYPE_APPLICATION_OVERLAY 로 설정

            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT
        )
        // 위치 지정
        params.gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL

        // activity_overlay.xml 불러오기
        val mView = inflate.inflate(R.layout.activity_overlay,null)
        val overlayButton :Button = mView.findViewById(R.id.btn)
        overlayButton.setOnClickListener{
            Log.d("오버레이", "initView: 버튼 클릭")
        }
        windowManager.addView(mView,params)

    }

}