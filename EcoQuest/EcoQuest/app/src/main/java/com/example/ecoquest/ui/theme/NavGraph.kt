package com.example.ecoquest.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecoquest.ui.challenges.ChallengeListScreen
import com.example.ecoquest.ui.challenges.ChallengesViewModel
import com.example.ecoquest.ui.home.HomeScreen
import com.example.ecoquest.ui.progress.ProgressScreen

@Composable
fun AppNavGraph() {

    val nav = rememberNavController()

    // ✅ ViewModel compartilhado entre Desafios e Progresso
    val challengesVM: ChallengesViewModel = viewModel()

    NavHost(
        navController = nav,
        startDestination = "login"
    ) {

        // HOME
        composable("home") {
            HomeScreen(nav = nav)
        }

        // DESAFIOS
        composable("desafios") {
            ChallengeListScreen(
                nav = nav,
                vm = challengesVM
            )
        }

        // PROGRESSO
        composable("progresso") {
            ProgressScreen(
                nav = nav,
                vm = challengesVM
            )
        }

    }
}
