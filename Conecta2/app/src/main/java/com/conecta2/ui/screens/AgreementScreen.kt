package com.conecta2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conecta2.data.UserProfile
import com.conecta2.ui.theme.LocalProfileColors

@Composable
fun AgreementScreen(
    onBack: () -> Unit
) {
    val colors = LocalProfileColors.current
    val clauses = remember {
        listOf(
            "Usaré internet de forma responsable y respetuosa.",
            "No compartiré información personal con desconocidos.",
            "Pediré permiso antes de descargar aplicaciones nuevas.",
            "Respetaré los límites de tiempo de pantalla establecidos.",
            "No aceptaré solicitudes de amistad de personas que no conozco.",
            "Informaré a un adulto si veo contenido inapropiado.",
            "Usaré contraseñas seguras y no las compartiré.",
            "Seré amable y respetuoso/a en línea."
        )
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
                    text = "Convenio Familiar Digital",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.textPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Establece reglas claras para el uso de internet",
                fontSize = 14.sp,
                color = colors.textSecondary
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(clauses.size) { index ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = colors.secondary)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${index + 1}.",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = colors.primary,
                                modifier = Modifier.width(32.dp)
                            )
                            Text(
                                text = clauses[index],
                                fontSize = 14.sp,
                                color = androidx.compose.ui.graphics.Color.White
                            )
                        }
                    }
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(
                        onClick = { /* Generar PDF */ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colors.primary)
                    ) {
                        Text("Generar PDF")
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    OutlinedButton(
                        onClick = { /* Compartir PDF */ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Compartir PDF")
                    }
                }
            }
        }
    }
}
