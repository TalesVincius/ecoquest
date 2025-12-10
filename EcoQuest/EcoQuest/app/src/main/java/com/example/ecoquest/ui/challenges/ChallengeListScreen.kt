// com/example/ecoquest/ui/challenges/ChallengeListScreen.kt
package com.example.ecoquest.ui.challenges

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecoquest.data.model.Challenge
import com.example.ecoquest.data.model.ChallengeCategory
import com.example.ecoquest.data.model.ChallengeDifficulty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChallengeListScreen(
    nav: NavController,
    vm: ChallengesViewModel
) {
    val challenges by vm.challenges.collectAsState()

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
                text = "Desafios de hoje 🌎",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Marque o que você já completou para somar pontos verdes ao planeta 🌱",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(challenges) { challenge ->
                    ChallengeCard(
                        challenge = challenge,
                        onToggle = { vm.toggleChallenge(challenge.id) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChallengeCard(
    challenge: Challenge,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {

            Checkbox(
                checked = challenge.isCompleted,
                onCheckedChange = { onToggle() }
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                // Linha título + pontos
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = challenge.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${challenge.points} pts",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = challenge.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                when (challenge.category) {
                                    ChallengeCategory.WATER -> "💧 Água"
                                    ChallengeCategory.PLASTIC -> "♻️ Plástico"
                                }
                            )
                        }
                    )
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                when (challenge.difficulty) {
                                    ChallengeDifficulty.EASY -> "Fácil"
                                    ChallengeDifficulty.MEDIUM -> "Médio"
                                    ChallengeDifficulty.HARD -> "Difícil"
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}
