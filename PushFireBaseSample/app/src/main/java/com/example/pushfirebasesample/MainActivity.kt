package com.example.pushfirebasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private val resultTextView : TextView by lazy { findViewById(R.id.resultTextView) }
    private val firebaseToken : TextView by lazy { findViewById(R.id.firebaseTokenTextView) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFirebase()

    }
    private fun initFirebase(){
        //파이어 베이스 토큰 얻기
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                val token = task.result
                firebaseToken.text = token
                Log.d("k4keye","token :"+token);
            }
        }
    }
}