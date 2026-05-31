package com.conecta2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conecta2.data.MatchItem
import com.conecta2.data.matchQuestionsPool
import com.conecta2.ui.theme.LocalProfileColors

@Composable
fun MatchGameScreen(
    onBack: () -> Unit
) {
    val colors = LocalProfileColors.current
    
    var gameItems by remember { mutableStateOf<List<Pair<MatchItem, MatchItem>>>(emptyList()) }
    var selectedTerm by remember { mutableStateOf<MatchItem?>(null) }
    var selectedDef by remember { mutableStateOf<MatchItem?>(null) }
    var matchedPairs by remember { mutableStateOf<List<Int>>(emptyList()) }
    var score by remember { mutableStateOf(0) }
    
    LaunchedEffect(Unit) {
        // Seleccionar 10 preguntas aleatorias
        val shuffled = matchQuestionsPool.shuffled().take(10)
        val definitions = shuffled.map { it.copy(id = it.id + 100) }.shuffled()
        gameItems = shuffled.zip(definitions)
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = colors.textPrimary)
                }
                Text(
                    text = "Empareja conceptos",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.textPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Puntaje: $score/${gameItems.size}",
                fontSize = 14.sp,
                color = colors.textSecondary
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Dos columnas del mismo tamaño
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Columna de términos
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    gameItems.forEach { (term, def) ->
                        if (!matchedPairs.contains(term.id)) {
                            MatchCard(
                                text = term.term,
                                isSelected = selectedTerm?.id == term.id,
                                isMatched = false,
                                onClick = { selectedTerm = term },
                                colors = colors,
                                isTerm = true
                            )
                        }
                    }
                }
                
                // Columna de definiciones
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    gameItems.forEach { (term, def) ->
                        if (!matchedPairs.contains(term.id)) {
                            MatchCard(
                                text = def.definition,
                                isSelected = selectedDef?.id == def.id,
                                isMatched = false,
                                onClick = { selectedDef = def },
                                colors = colors,
                                isTerm = false
                            )
                        }
                    }
                }
            }
        }
        
        // Mensaje de completado
        if (matchedPairs.size == gameItems.size && gameItems.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.padding(32.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = colors.background)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            tint = colors.primary,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "¡Completado!",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.textPrimary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Puntaje final: $score/${gameItems.size}",
                            fontSize = 18.sp,
                            color = colors.textSecondary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                val shuffled = matchQuestionsPool.shuffled().take(10)
                                val definitions = shuffled.map { it.copy(id = it.id + 100) }.shuffled()
                                gameItems = shuffled.zip(definitions)
                                matchedPairs = emptyList()
                                score = 0
                                selectedTerm = null
                                selectedDef = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = colors.primary)
                        ) {
                            Text("Jugar otra vez")
                        }
                    }
                }
            }
        }
    }
    
    // Verificar emparejamiento
    LaunchedEffect(selectedTerm, selectedDef) {
        if (selectedTerm != null && selectedDef != null) {
            val pair = gameItems.find { it.first.id == selectedTerm!!.id }
            if (pair != null && pair.second.id == selectedDef!!.id) {
                // Correcto
                matchedPairs = matchedPairs + pair.first.id
                score = score + 1
            }
            selectedTerm = null
            selectedDef = null
        }
    }
}

@Composable
fun MatchCard(
    text: String,
    isSelected: Boolean,
    isMatched: Boolean,
    onClick: () -> Unit,
    colors: com.conecta2.ui.theme.ProfileColors,
    isTerm: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isMatched, onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when {
                isMatched -> colors.accent
                isSelected -> colors.primary
                else -> colors.secondary
            }
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = androidx.compose.ui.graphics.Color.White,
                maxLines = 3
            )
        }
    }
}
