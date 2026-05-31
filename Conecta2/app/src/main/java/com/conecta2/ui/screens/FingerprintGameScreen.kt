package com.conecta2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.conecta2.data.ServiceData
import com.conecta2.data.servicesList
import com.conecta2.ui.theme.LocalProfileColors

@Composable
fun FingerprintGameScreen(
    onBack: () -> Unit
) {
    val colors = LocalProfileColors.current
    var selectedServices by remember { mutableStateOf<List<ServiceData>>(emptyList()) }
    var showSummary by remember { mutableStateOf(false) }
    var showBubble by remember { mutableStateOf<ServiceData?>(null) }
    
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
                    text = "¿Qué información dejas en internet?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.textPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Toca los servicios que usas para ver tu huella",
                fontSize = 14.sp,
                color = colors.textSecondary
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Barra de progreso/escala
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(colors.primary, colors.accent)
                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(selectedServices.size / 20f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(colors.accent.copy(alpha = 0.7f))
                )
            }
            
            Text(
                text = "${selectedServices.size}/20 servicios seleccionados",
                fontSize = 12.sp,
                color = colors.textSecondary,
                modifier = Modifier.padding(top = 4.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Grid de servicios
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                servicesList.chunked(3).forEach { row ->
                    item {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row.forEach { service ->
                                ServiceChip(
                                    service = service,
                                    isSelected = selectedServices.contains(service),
                                    onClick = {
                                        if (selectedServices.contains(service)) {
                                            selectedServices = selectedServices - service
                                        } else {
                                            selectedServices = selectedServices + service
                                        }
                                    },
                                    onLongClick = { showBubble = service },
                                    colors = colors
                                )
                            }
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Botones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { showSummary = true },
                    modifier = Modifier.weight(1f),
                    enabled = selectedServices.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(containerColor = colors.primary)
                ) {
                    Text("Ver mi huella")
                }
                
                OutlinedButton(
                    onClick = { selectedServices = emptyList() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = colors.textPrimary)
                ) {
                    Text("Reiniciar")
                }
            }
        }
        
        // Bubble de información
        showBubble?.let { service ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { showBubble = null }
            ) {
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = colors.primary)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = service.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = androidx.compose.ui.graphics.Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Datos expuestos:",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.9f)
                        )
                        service.dataExposed.forEach { data ->
                            Text(
                                text = "• $data",
                                fontSize = 12.sp,
                                color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }
        }
    }
    
    // Dialog de resumen
    if (showSummary) {
        SummaryDialog(
            services = selectedServices,
            colors = colors,
            onDismiss = { showSummary = false }
        )
    }
}

@Composable
fun ServiceChip(
    service: ServiceData,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    colors: com.conecta2.ui.theme.ProfileColors
) {
    Surface(
        modifier = Modifier
            .weight(1f)
            .aspectRatio(2.5f),
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) colors.accent else colors.secondary,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = service.name,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = androidx.compose.ui.graphics.Color.White,
                maxLines = 2
            )
        }
    }
}

@Composable
fun SummaryDialog(
    services: List<ServiceData>,
    colors: com.conecta2.ui.theme.ProfileColors,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Resumen de tu huella digital") },
        text = {
            LazyColumn {
                items(services.size) { index ->
                    val service = services[index]
                    Text(
                        text = "• ${service.name}: ${service.dataExposed.size} tipos de datos",
                        fontSize = 14.sp
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Entendido")
            }
        },
        containerColor = colors.background
    )
}
