package com.conecta2.ui.theme

import androidx.compose.ui.graphics.Color

// Paleta de colores naranjas
val OrangeDark = Color(0xFFaf5700)
val OrangeDarkMid = Color(0xFFc06500)
val OrangeMidDark = Color(0xFFd27300)
val OrangeMid = Color(0xFFe48200)
val OrangeMidLight = Color(0xFFf69100)
val OrangePrimary = Color(0xFFffa333)
val OrangeLight = Color(0xFFffba6a)
val OrangeLighter = Color(0xFFffd19b)
val OrangeCream = Color(0xFFffe8cd)
val White = Color(0xFFFFFFFF)

// Modo oscuro
val DarkBackground = Color(0xFF1E1E2E)

// Colores por perfil y tema
data class ProfileColors(
    val background: Color,
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val textPrimary: Color,
    val textSecondary: Color
)

val AdultLightColors = ProfileColors(
    background = White,
    primary = OrangePrimary,
    secondary = OrangeMidLight,
    accent = OrangeMidDark,
    textPrimary = OrangeDark,
    textSecondary = OrangeDarkMid
)

val TeenLightColors = ProfileColors(
    background = OrangeCream,
    primary = OrangeLight,
    secondary = OrangePrimary,
    accent = OrangeMid,
    textPrimary = OrangeDarkMid,
    textSecondary = OrangeMidDark
)

val DarkColors = ProfileColors(
    background = DarkBackground,
    primary = OrangeMidLight,
    secondary = OrangeMidDark,
    accent = OrangeDark,
    textPrimary = White,
    textSecondary = OrangeLight
)
