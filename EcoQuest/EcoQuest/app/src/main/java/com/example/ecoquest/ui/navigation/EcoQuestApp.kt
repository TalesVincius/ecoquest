// com/example/ecoquest/ui/navigation/EcoQuestApp.kt
package com.example.ecoquest.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.ecoquest.ui.challenges.ChallengeListScreen
import com.example.ecoquest.ui.challenges.ChallengesViewModel
import com.example.ecoquest.ui.home.HomeScreen
import com.example.ecoquest.ui.login.LoginScreen
import com.example.ecoquest.ui.progress.ProgressScreen
import com.example.ecoquest.ui.tips.DailyTipsScreen

@OptIn(ExperimentalMaterial3Api::class) // ✅ AQUI
@Composable
fun EcoQuestApp() {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val challengesVM: ChallengesViewModel = viewModel()

    Scaffold(
        topBar = {
            EcoQuestTopBar(
                currentRoute = currentRoute,
                canNavigateBack = navController.previousBackStackEntry != null,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("login") {
                LoginScreen(nav = navController)
            }
            composable("home") {
                HomeScreen(nav = navController)
            }
            composable("desafios") {
                ChallengeListScreen(
                    nav = navController,
                    vm = challengesVM
                )
            }
            composable("progresso") {
                ProgressScreen(
                    nav = navController,
                    vm = challengesVM
                )
            }
            composable("dicas") {
                DailyTipsScreen(nav = navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // ✅ AQUI TAMBÉM
@Composable
private fun EcoQuestTopBar(
    currentRoute: String?,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit
) {
    val title = when (currentRoute) {
        "login" -> "EcoQuest 🌱"
        "home" -> "EcoQuest 🌱"
        "desafios" -> "Desafios de hoje 🌎"
        "progresso" -> "Seu progresso 📈"
        "dicas" -> "Dicas ecológicas 🌿"
        else -> "EcoQuest"
    }

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (canNavigateBack && currentRoute != "login") {
                TextButton(onClick = onBackClick) {
                    Text("← Voltar")
                }
            }
        }
    )
}
