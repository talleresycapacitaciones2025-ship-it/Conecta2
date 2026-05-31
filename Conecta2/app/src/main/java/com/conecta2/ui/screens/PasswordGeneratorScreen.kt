package com.conecta2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conecta2.ui.theme.LocalProfileColors
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun PasswordGeneratorScreen(
    onBack: () -> Unit
) {
    val colors = LocalProfileColors.current
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    
    var passwordLength by remember { mutableStateOf(12) }
    var useUppercase by remember { mutableStateOf(true) }
    var useNumbers by remember { mutableStateOf(true) }
    var useSymbols by remember { mutableStateOf(true) }
    var generatedPassword by remember { mutableStateOf("") }
    var isAnimating by remember { mutableStateOf(false) }
    var displayPassword by remember { mutableStateOf("") }
    
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
                    text = "Generador de Contraseñas",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.textPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Password display
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colors.primary)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (displayPassword.isEmpty()) "Tu contraseña" else displayPassword,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = androidx.compose.ui.graphics.Color.White,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Strength indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Débil", color = colors.textSecondary, fontSize = 12.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    when {
                                        index < getStrengthLevel(generatedPassword) -> colors.accent
                                        else -> colors.secondary.copy(alpha = 0.3f)
                                    },
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }
                Text("Fuerte", color = colors.textSecondary, fontSize = 12.sp)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Length slider
            Text(
                text = "Longitud: $passwordLength",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colors.textPrimary
            )
            
            Slider(
                value = passwordLength.toFloat(),
                onValueChange = { passwordLength = it.toInt() },
                valueRange = 8f..20f,
                steps = 11,
                colors = SliderDefaults.colors(thumbColor = colors.primary, activeTrackColor = colors.primary)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Options
            CheckboxRow("Mayúsculas (A-Z)", useUppercase, { useUppercase = it }, colors)
            CheckboxRow("Números (0-9)", useNumbers, { useNumbers = it }, colors)
            CheckboxRow("Símbolos (!@#$)", useSymbols, { useSymbols = it }, colors)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Generate button
            Button(
                onClick = {
                    isAnimating = true
                    generatePasswordWithAnimation(
                        length = passwordLength,
                        uppercase = useUppercase,
                        numbers = useNumbers,
                        symbols = useSymbols,
                        onUpdate = { tempPwd -> displayPassword = tempPwd },
                        onComplete = { pwd ->
                            generatedPassword = pwd
                            displayPassword = pwd
                            isAnimating = false
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colors.accent),
                enabled = !isAnimating
            ) {
                Text(
                    text = if (isAnimating) "Generando..." else "Generar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Copy and Share buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        if (generatedPassword.isNotEmpty()) {
                            clipboardManager.setText(AnnotatedString(generatedPassword))
                        }
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Filled.ContentCopy, contentDescription = null, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Copiar")
                }
                
                OutlinedButton(
                    onClick = {
                        if (generatedPassword.isNotEmpty()) {
                            val sendIntent = android.content.Intent().apply {
                                action = android.content.Intent.ACTION_SEND
                                putExtra(android.content.Intent.EXTRA_TEXT, generatedPassword)
                                type = "text/plain"
                            }
                            context.startActivity(android.content.Intent.createChooser(sendIntent, "Compartir contraseña"))
                        }
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Filled.Share, contentDescription = null, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Compartir")
                }
            }
        }
    }
}

@Composable
fun CheckboxRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    colors: com.conecta2.ui.theme.ProfileColors
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(checkedColor = colors.primary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            color = colors.textPrimary
        )
    }
}

fun getStrengthLevel(password: String): Int {
    if (password.length < 8) return 0
    var strength = 0
    if (password.length >= 12) strength++
    if (password.any { it.isUpperCase() }) strength++
    if (password.any { it.isDigit() }) strength++
    if (password.any { !it.isLetterOrDigit() }) strength++
    return when {
        strength >= 4 -> 3
        strength >= 2 -> 2
        strength >= 1 -> 1
        else -> 0
    }
}

fun generatePasswordWithAnimation(
    length: Int,
    uppercase: Boolean,
    numbers: Boolean,
    symbols: Boolean,
    onUpdate: (String) -> Unit,
    onComplete: (String) -> Unit
) {
    val chars = buildString {
        append('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
               'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
        if (uppercase) append('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                              'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')
        if (numbers) append('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        if (symbols) append('!', '@', '#', '$', '%', '&', '*', '(', ')', '-', '_', '=', '+')
    }
    
    // Usar coroutine scope apropiado
    kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
        // Animación de tragamonedas
        repeat(15) { iteration ->
            val tempPassword = (1..length)
                .map { chars[Random.nextInt(chars.length)] }
                .joinToString("")
            onUpdate(tempPassword)
            delay(80)
        }
        
        // Generar contraseña final
        val finalPassword = (1..length)
            .map { chars[Random.nextInt(chars.length)] }
            .joinToString("")
        
        onComplete(finalPassword)
    }
}
