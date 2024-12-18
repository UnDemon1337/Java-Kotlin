package com.example.chaikinweather.receiver

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.chaikinweather.R
import com.example.chaikinweather.storage.SharedPreferencesManager
import org.koin.java.KoinJavaComponent.inject

class NotificationReceiver : BroadcastReceiver() {

    private val sharedPreferencesManager: SharedPreferencesManager by inject(SharedPreferencesManager::class.java)

    override fun onReceive(context: Context, intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                return
            }
        }

        if (sharedPreferencesManager.getPushNotificationsState()) {
            sendMorningNotification(context)
        }
    }

    private fun sendMorningNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "morning_notification_channel",
                "Утреннее уведомление",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, "morning_notification_channel")
            .setSmallIcon(R.drawable.notifications_24px)
            .setContentTitle("Прогноз на день")
            .setContentText("Не забудьте проверить погоду!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
    }
}
