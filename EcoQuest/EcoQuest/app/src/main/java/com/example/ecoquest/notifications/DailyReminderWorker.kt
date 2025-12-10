package com.example.ecoquest.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.ecoquest.R

class DailyReminderWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        createNotificationChannel()
        showNotification()
        return Result.success()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Lembretes diários"
            val descriptionText = "Notificações para lembrar dos desafios ecológicos"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(
                CHANNEL_ID,
                name,
                importance
            ).apply {
                description = descriptionText
            }

            val notificationManager = applicationContext
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification() {
        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // pode trocar por um ícone seu
            .setContentTitle("EcoQuest 🌱")
            .setContentText("Bora salvar o planeta hoje? Novo desafio esperando 💚")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    companion object {
        const val CHANNEL_ID = "ecoquest_daily_reminders"
        const val NOTIFICATION_ID = 1001
    }
}
