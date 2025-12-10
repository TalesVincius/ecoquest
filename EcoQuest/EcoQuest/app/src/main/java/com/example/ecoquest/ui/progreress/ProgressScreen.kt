// com/example/ecoquest/ui/progress/ProgressScreen.kt
package com.example.ecoquest.ui.progress

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecoquest.data.model.ChallengeCategory
import com.example.ecoquest.ui.challenges.ChallengesViewModel

@Composable
fun ProgressScreen(
    nav: NavController,
    vm: ChallengesViewModel
) {
    val challenges by vm.challenges.collectAsState()

    val total = challenges.size.coerceAtLeast(1)
    val completed = challenges.count { it.isCompleted }
    val progress = completed.toFloat() / total.toFloat()

    val byCategory = challenges.groupBy { it.category }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Card de progresso geral
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(72.dp)
                ) {
                    CircularProgressIndicator(
                        progress = progress,
                        strokeWidth = 6.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Column {
                    Text(
                        text = "$completed de $total desafios concluídos",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Cada pequena ação conta para um planeta mais verde 🌿",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }

        Text(
            text = "Por categoria",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )

        byCategory.forEach { (category, list) ->
            val totalCat = list.size.coerceAtLeast(1)
            val doneCat = list.count { it.isCompleted }
            val progCat = doneCat.toFloat() / totalCat.toFloat()

            val label = when (category) {
                ChallengeCategory.WATER -> "💧 Água"
                ChallengeCategory.PLASTIC -> "♻️ Plástico"
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    LinearProgressIndicator(
                        progress = progCat,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "$doneCat de $totalCat concluídos",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
