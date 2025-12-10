package com.example.ecoquest.ui.tips

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class EcoTip(
    val id: Int,
    val title: String,
    val description: String,
    val category: String
)

@Composable
fun TipsScreen(nav: NavController) {

    // Lista fixa de dicas
    val tips = remember {
        listOf(
            EcoTip(
                1,
                "Banho mais curto",
                "Tente reduzir seu banho em 3 minutos hoje. Isso economiza MUITA água ao longo do mês.",
                "Água"
            ),
            EcoTip(
                2,
                "Feche a torneira",
                "Escove os dentes e ensaboe a louça com a torneira fechada. Abra só para enxaguar.",
                "Água"
            ),
            EcoTip(
                3,
                "Garrafa reutilizável",
                "Use uma garrafinha reutilizável em vez de comprar água em garrafas plásticas.",
                "Plástico"
            ),
            EcoTip(
                4,
                "Recuse canudos",
                "Hoje, recuse canudos e talheres descartáveis quando puder.",
                "Plástico"
            ),
            EcoTip(
                5,
                "Desligue os stand-by",
                "Tire da tomada aparelhos que não está usando (TV, videogame, carregadores).",
                "Energia"
            ),
            EcoTip(
                6,
                "Aproveite a luz natural",
                "Abra janelas e cortinas para usar menos lâmpadas durante o dia.",
                "Energia"
            )
        )
    }

    // "Dica do dia" simples usando o dia atual em milissegundos
    val dayIndex = remember {
        (System.currentTimeMillis() / 86_400_000L).toInt()
    }
    val tipOfDay = tips[dayIndex % tips.size]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Título
        Text(
            text = "Dica do dia 💡",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.primary
        )

        // Card da dica do dia
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = tipOfDay.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = tipOfDay.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Categoria: ${tipOfDay.category}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }

        // Lista de outras dicas
        Text(
            text = "Outras dicas rápidas",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tips.filter { it.id != tipOfDay.id }) { tip ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = tip.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Text(
                            text = tip.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                        )
                        Text(
                            text = tip.category,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
