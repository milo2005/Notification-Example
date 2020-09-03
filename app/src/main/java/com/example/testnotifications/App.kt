package com.example.testnotifications

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App : Application(){



    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val fcmChannel = NotificationChannel(
                getString(R.string.notification_channel_id), "pidotax", NotificationManager.IMPORTANCE_HIGH
            )
            fcmChannel.description = getString(R.string.notification_channel_description)
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(fcmChannel)
        }
    }

}