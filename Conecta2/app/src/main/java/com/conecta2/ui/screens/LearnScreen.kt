package com.conecta2.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conecta2.data.CardCategory
import com.conecta2.data.LearnCard
import com.conecta2.data.learnCards
import com.conecta2.ui.theme.LocalProfileColors

@Composable
fun LearnScreen(
    onBack: () -> Unit
) {
    val colors = LocalProfileColors.current
    var selectedTab by remember { mutableStateOf(0) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = colors.textPrimary)
                }
                Text(
                    text = "Aprende",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.textPrimary
                )
            }
            
            // Tabs
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = colors.primary,
                contentColor = androidx.compose.ui.graphics.Color.White
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Peligros") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Protección") }
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = { Text("Familias") }
                )
            }
            
            // Cards grid
            val category = when (selectedTab) {
                0 -> CardCategory.DANGERS
                1 -> CardCategory.PROTECTION
                else -> CardCategory.FAMILY
            }
            
            val cards = learnCards.filter { it.category == category }
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(cards.size) { index ->
                    FlipCard(card = cards[index], colors = colors)
                }
            }
        }
    }
}

@Composable
fun FlipCard(
    card: LearnCard,
    colors: com.conecta2.ui.theme.ProfileColors
) {
    var isFlipped by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()
    
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable { isFlipped = !isFlipped },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isFlipped) colors.accent else colors.secondary
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (!isFlipped) {
                // Front
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(
                        imageVector = when (card.category) {
                            CardCategory.DANGERS -> Icons.Filled.Dangerous
                            CardCategory.PROTECTION -> Icons.Filled.Lock
                            CardCategory.FAMILY -> Icons.Filled.People
                        },
                        contentDescription = null,
                        tint = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = card.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = androidx.compose.ui.graphics.Color.White,
                        maxLines = 2
                    )
                }
            } else {
                // Back
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = card.content,
                        fontSize = 11.sp,
                        color = androidx.compose.ui.graphics.Color.White,
                        maxLines = 5
                    )
                }
            }
        }
    }
}
