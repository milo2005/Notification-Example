package com.example.testnotifications.services

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.testnotifications.MainActivity
import com.example.testnotifications.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService(){

    private var broadcaster:LocalBroadcastManager? = null

    override fun onCreate() {
        super.onCreate()
        broadcaster = LocalBroadcastManager.getInstance(this)
    }

    override fun onNewToken(token: String) {
        // Este metodo es super importante
        // Aqui toca actualizare el modelo de driver
        // y agregarle el token
        Log.i("TOKE_NOT", token)
    }

    override fun onMessageReceived(remote: RemoteMessage) {
        remote.notification?.let {
            showNotification(this, it.title!!, it.body!!)
        }
    }

    private fun showNotification(context: Context, title: String, body: String) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            123,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val notificationBuilder =
            NotificationCompat.Builder(
                context,
                context.getString(R.string.notification_channel_id)
            )
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX)


        val notifyId = System.currentTimeMillis().toInt()

        with(NotificationManagerCompat.from(context)){
            notify(notifyId, notificationBuilder.build())
        }
    }

}