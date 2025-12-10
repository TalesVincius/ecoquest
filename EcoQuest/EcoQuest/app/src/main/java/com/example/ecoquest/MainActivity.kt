package com.example.ecoquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ecoquest.ui.navigation.EcoQuestApp
import com.example.ecoquest.ui.theme.EcoQuestTheme
import com.example.ecoquest.notifications.NotificationScheduler

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Agenda as notificações diárias (apenas uma vez, WorkManager garante)
        NotificationScheduler.scheduleDailyReminder(this)

        setContent {
            EcoQuestTheme {
                EcoQuestApp()
            }
        }
    }
}
