package com.conecta2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conecta2.data.UserProfile
import com.conecta2.ui.theme.LocalProfileColors

@Composable
fun HomeScreen(
    onNavigateToGames: () -> Unit,
    onNavigateToLearn: () -> Unit,
    onNavigateToFingerprint: () -> Unit,
    onNavigateToMatch: () -> Unit,
    onNavigateToPassword: () -> Unit,
    onNavigateToAgreement: () -> Unit,
    currentProfile: UserProfile?
) {
    val colors = LocalProfileColors.current
    val isAdult = currentProfile == UserProfile.ADULT
    
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
            // Saludo
            Text(
                text = if (isAdult) "¡Hola, cuidador/a!" else "¡Hola, aventurero/a!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colors.textPrimary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = if (isAdult) "Gestiona la seguridad digital de tu familia" else "Aprende a navegar seguro en internet",
                fontSize = 16.sp,
                color = colors.textSecondary
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Grid de opciones
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    HomeCard(
                        title = "Huella Digital",
                        icon = Icons.Filled.Fingerprint,
                        onClick = onNavigateToFingerprint,
                        colors = colors
                    )
                }
                item {
                    HomeCard(
                        title = "Emparejar",
                        icon = Icons.Filled.Puzzle,
                        onClick = onNavigateToMatch,
                        colors = colors
                    )
                }
                item {
                    HomeCard(
                        title = "Contraseñas",
                        icon = Icons.Filled.Lock,
                        onClick = onNavigateToPassword,
                        colors = colors
                    )
                }
                if (isAdult) {
                    item {
                        HomeCard(
                            title = "Convenio",
                            icon = Icons.Filled.Description,
                            onClick = onNavigateToAgreement,
                            colors = colors
                        )
                    }
                }
                item {
                    HomeCard(
                        title = "Juegos",
                        icon = Icons.Filled.VideogameAsset,
                        onClick = onNavigateToGames,
                        colors = colors
                    )
                }
                item {
                    HomeCard(
                        title = "Aprende",
                        icon = Icons.Filled.School,
                        onClick = onNavigateToLearn,
                        colors = colors
                    )
                }
            }
        }
    }
}

@Composable
fun HomeCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    colors: com.conecta2.ui.theme.ProfileColors
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colors.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = androidx.compose.ui.graphics.Color.White,
                maxLines = 2
            )
        }
    }
}
