package com.example.shakeapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() ,SensorEventListener{
    private val TAG = "로그"
    private lateinit var sensorManager:SensorManager

    private var accel :Float = 0.0f
    private var accelCurrent :Float = 0.0f
    private var accelLast :Float = 0.0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        accel = 10f
        accelCurrent = SensorManager.GRAVITY_EARTH //센서를 지구 중력으로
        accelLast = SensorManager.GRAVITY_EARTH
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d(TAG, "onAccuracyChanged: ")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val x:Float = event?.values?.get(0) as Float
        val y:Float = event?.values?.get(1) as Float
        val z:Float = event?.values?.get(2) as Float
        //Log.d(TAG, "onSensorChanged: \nx = ${x.toInt()} ,\ny = ${y.toInt()} ,\nz = ${z.toInt()}")

        accelLast = accelCurrent

        accelCurrent = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        val delta : Float = accelCurrent - accelLast

        accel = accel * 0.9f + delta

        Log.d(TAG, "onSensorChanged: accel = $accel")
        //흔들렸다
        if(accel > 10){

            Log.d(TAG, "onSensorChanged: 흔들었음")
        }

    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        sensorManager.unregisterListener(this)
        super.onPause()

    }


}