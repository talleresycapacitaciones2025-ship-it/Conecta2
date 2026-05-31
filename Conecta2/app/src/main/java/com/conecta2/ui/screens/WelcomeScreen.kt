package com.conecta2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conecta2.data.UserProfile
import com.conecta2.ui.theme.LocalProfileColors
import com.conecta2.ui.theme.isAdultLightProfile
import com.conecta2.ui.theme.isTeenLightProfile

@Composable
fun WelcomeScreen(
    onProfileSelected: (UserProfile) -> Unit
) {
    val colors = LocalProfileColors.current
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icono o logo
            Text(
                text = "Conecta2",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = colors.primary
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "¡Bienvenido/a a Conecta2!",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.textPrimary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Tu app de ciberseguridad familiar",
                fontSize = 16.sp,
                color = colors.textSecondary
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Botón Adulto
            Button(
                onClick = { onProfileSelected(UserProfile.ADULT) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary
                )
            ) {
                Text(
                    text = "Persona adulta / Cuidador/a",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isAdultLightProfile(colors)) colors.textPrimary else androidx.compose.ui.graphics.Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Botón Teen
            Button(
                onClick = { onProfileSelected(UserProfile.TEEN) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.secondary
                )
            ) {
                Text(
                    text = "Aventurera / Aventurero",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isTeenLightProfile(colors)) colors.textPrimary else androidx.compose.ui.graphics.Color.White
                )
            }
        }
    }
}
