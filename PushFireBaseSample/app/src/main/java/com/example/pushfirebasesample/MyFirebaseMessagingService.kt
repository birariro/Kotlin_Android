package com.example.pushfirebasesample

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
    //firebase 에서 수신된 메시지는 일로 수신된다.
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
}