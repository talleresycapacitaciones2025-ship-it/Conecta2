package com.conecta2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.conecta2.ui.theme.LocalProfileColors

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onClearHistory: () -> Unit
) {
    val colors = LocalProfileColors.current
    var showConfirmDialog by remember { mutableStateOf(false) }
    var selectedTheme by remember { mutableStateOf("system") }
    
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
                    text = "Ajustes",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.textPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Theme setting
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colors.secondary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Tema",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = androidx.compose.ui.graphics.Color.White
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ThemeOption("Claro", selectedTheme == "light", { selectedTheme = "light" }, colors)
                        ThemeOption("Oscuro", selectedTheme == "dark", { selectedTheme = "dark" }, colors)
                        ThemeOption("Sistema", selectedTheme == "system", { selectedTheme = "system" }, colors)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Clear history
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colors.accent)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Limpiar historial",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = androidx.compose.ui.graphics.Color.White
                        )
                        Text(
                            text = "Borra todos los datos guardados",
                            fontSize = 12.sp,
                            color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.8f)
                        )
                    }
                    
                    IconButton(
                        onClick = { showConfirmDialog = true }
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Eliminar",
                            tint = androidx.compose.ui.graphics.Color.White
                        )
                    }
                }
            }
        }
        
        // Confirm dialog
        if (showConfirmDialog) {
            AlertDialog(
                onDismissRequest = { showConfirmDialog = false },
                title = { Text("¿Estás seguro/a?") },
                text = { Text("Se borrarán todos los datos guardados, incluyendo tu perfil y puntajes.") },
                confirmButton = {
                    Button(
                        onClick = {
                            onClearHistory()
                            showConfirmDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colors.accent)
                    ) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { showConfirmDialog = false }) {
                        Text("Cancelar")
                    }
                },
                containerColor = colors.background,
                titleContentColor = colors.textPrimary,
                textContentColor = colors.textSecondary
            )
        }
    }
}

@Composable
fun ThemeOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    colors: com.conecta2.ui.theme.ProfileColors
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(label, color = if (selected) androidx.compose.ui.graphics.Color.White else colors.textPrimary) },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = if (selected) colors.primary else colors.secondary,
            labelColor = androidx.compose.ui.graphics.Color.White
        )
    )
}
