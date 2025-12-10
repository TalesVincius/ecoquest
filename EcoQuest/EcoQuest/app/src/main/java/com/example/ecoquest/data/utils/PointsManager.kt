package com.example.ecoquest.utils

import android.content.Context
import android.content.SharedPreferences

class PointsManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getTotalPoints(): Int = prefs.getInt(KEY_POINTS, 0)

    fun addPoints(points: Int) {
        val current = getTotalPoints()
        prefs.edit()
            .putInt(KEY_POINTS, current + points)
            .apply()
    }

    fun getCompletedChallenges(): Int = prefs.getInt(KEY_COMPLETED, 0)

    fun incrementCompletedChallenges() {
        val current = getCompletedChallenges()
        prefs.edit()
            .putInt(KEY_COMPLETED, current + 1)
            .apply()
    }

    companion object {
        private const val PREFS_NAME = "ecoquest_prefs"
        private const val KEY_POINTS = "total_points"
        private const val KEY_COMPLETED = "completed_challenges"
    }
}
