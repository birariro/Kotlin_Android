package com.example.kmm_helloworld

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}