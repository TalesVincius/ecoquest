// com/example/ecoquest/ui/tips/DailyTipsScreen.kt
package com.example.ecoquest.ui.tips

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DailyTipsScreen(nav: NavController) {

    val tips = listOf(
        "Leve sua própria garrafinha reutilizável 💧",
        "Desligue a torneira enquanto escova os dentes 🪥",
        "Reaproveite potes de vidro como organizadores ♻️",
        "Use sacolas retornáveis nas compras do mercado 🛍️",
        "Separe o lixo reciclável sempre que puder 🗑️",
        "Desligue luzes de cômodos vazios 💡",
        "Prefira andar a pé ou de bicicleta em trajetos curtos 🚶‍♀️🚲",
        "Reutilize folhas de papel para rascunho 📄",
        "Doe roupas em bom estado ao invés de jogar fora 👕",
        "Plante uma muda ou cuide de uma plantinha 🌱"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Dicas ecológicas do dia 🌿",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Pequenas atitudes que você pode colocar em prática hoje para cuidar do planeta.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(tips) { tip ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = tip,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}
