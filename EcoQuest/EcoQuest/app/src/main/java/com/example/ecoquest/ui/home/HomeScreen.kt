// com/example/ecoquest/ui/home/HomeScreen.kt
package com.example.ecoquest.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(nav: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "EcoQuest 🌱",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Desafios ecológicos diários e dicas práticas para um dia mais sustentável.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                Button(
                    onClick = { nav.navigate("desafios") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Desafios de hoje 🌎")
                }

                Button(
                    onClick = { nav.navigate("progresso") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Meu progresso 📈")
                }

                Button(
                    onClick = { nav.navigate("dicas") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Dicas ecológicas do dia 🌿")
                }
            }
        }
    }
}
