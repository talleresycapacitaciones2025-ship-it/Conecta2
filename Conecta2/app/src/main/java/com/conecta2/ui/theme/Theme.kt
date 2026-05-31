package com.conecta2.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import com.conecta2.data.UserProfile
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun Conecta2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val viewModel: ThemeViewModel = viewModel()
    
    val currentProfile by viewModel.currentProfile.collectAsState(initial = null)
    val themeMode by viewModel.themeMode.collectAsState(initial = "system")
    
    val isDark = when (themeMode) {
        "dark" -> true
        "light" -> false
        else -> darkTheme
    }
    
    val colors = if (isDark) {
        DarkColors
    } else {
        when (currentProfile) {
            UserProfile.ADULT -> AdultLightColors
            UserProfile.TEEN -> TeenLightColors
            null -> AdultLightColors
        }
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
        }
    }
    
    CompositionLocalProvider(
        LocalProfileColors provides colors,
        LocalIsDarkTheme provides isDark,
        content = content
    )
}

object LocalProfileColors {
    companion object
}

private val LocalProfileColorsProvider = compositionLocalOf<ProfileColors> { error("No ProfileColors provided") }
val LocalProfileColors get() = LocalProfileColorsProvider.current

object LocalIsDarkTheme {
    companion object
}

private val LocalIsDarkThemeProvider = compositionLocalOf<Boolean> { error("No IsDarkTheme provided") }
val LocalIsDarkTheme get() = LocalIsDarkThemeProvider.current

class ThemeViewModel {
    private val _currentProfile = mutableStateOf<UserProfile?>(null)
    val currentProfile: State<UserProfile?> = _currentProfile
    
    private val _themeMode = mutableStateOf("system")
    val themeMode: State<String> = _themeMode
    
    fun setProfile(profile: UserProfile?) {
        _currentProfile.value = profile
    }
    
    fun setThemeMode(mode: String) {
        _themeMode.value = mode
    }
}
