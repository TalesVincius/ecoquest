package com.example.ecoquest.notifications

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

object NotificationScheduler {

    private const val UNIQUE_WORK_NAME = "ecoquest_daily_reminder"

    fun scheduleDailyReminder(context: Context) {
        val workManager = WorkManager.getInstance(context)

        // Próxima execução às 9h
        val now = Calendar.getInstance()
        val nextRun = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            // se já passou das 9h hoje, agenda pra amanhã
            if (before(now)) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        val initialDelay = nextRun.timeInMillis - now.timeInMillis

        val request = PeriodicWorkRequestBuilder<DailyReminderWorker>(
            1, TimeUnit.DAYS
        )
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .build()

        workManager.enqueueUniquePeriodicWork(
            UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }
}
