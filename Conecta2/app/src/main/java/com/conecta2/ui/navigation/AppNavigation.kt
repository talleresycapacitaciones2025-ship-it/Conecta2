package com.conecta2.ui.navigation

import android.content.Context
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.conecta2.data.Screen
import com.conecta2.data.UserProfile
import com.conecta2.ui.screens.*
import com.conecta2.ui.theme.Conecta2Theme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    
    var currentProfile by remember { mutableStateOf<UserProfile?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var hasLoadedProfile by remember { mutableStateOf(false) }
    
    // Cargar perfil solo una vez al iniciar
    LaunchedEffect(Unit) {
        if (!hasLoadedProfile) {
            try {
                // El perfil se carga cuando el usuario lo selecciona
                // Aquí solo inicializamos el estado
                currentProfile = null
            } catch (e: Exception) {
                currentProfile = null
            } finally {
                isLoading = false
                hasLoadedProfile = true
            }
        }
    }
    
    if (isLoading) {
        // Pantalla de carga simple
        androidx.compose.foundation.layout.Box(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .background(androidx.compose.ui.graphics.Color.White)
        ) {
            androidx.compose.material3.CircularProgressIndicator(
                modifier = androidx.compose.ui.Modifier.align(androidx.compose.ui.Alignment.Center),
                color = androidx.compose.ui.graphics.Color(0xFFffa333)
            )
        }
    } else {
        NavHost(
            navController = navController,
            startDestination = if (currentProfile == null && hasLoadedProfile) Screen.Welcome.route else Screen.Home.route
        ) {
            composable(Screen.Welcome.route) {
                WelcomeScreen(
                    onProfileSelected = { profile ->
                        currentProfile = profile
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    }
                )
            }
            
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToGames = { navController.navigate(Screen.Games.route) },
                    onNavigateToLearn = { navController.navigate(Screen.Learn.route) },
                    onNavigateToFingerprint = { navController.navigate(Screen.FingerprintGame.route) },
                    onNavigateToMatch = { navController.navigate(Screen.MatchGame.route) },
                    onNavigateToPassword = { navController.navigate(Screen.PasswordGenerator.route) },
                    onNavigateToAgreement = { navController.navigate(Screen.Agreement.route) },
                    currentProfile = currentProfile
                )
            }
            
            composable(Screen.Learn.route) {
                LearnScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            
            composable(Screen.Games.route) {
                GamesMenuScreen(
                    onBack = { navController.popBackStack() },
                    onNavigateToFingerprint = { navController.navigate(Screen.FingerprintGame.route) },
                    onNavigateToMatch = { navController.navigate(Screen.MatchGame.route) },
                    onNavigateToPassword = { navController.navigate(Screen.PasswordGenerator.route) }
                )
            }
            
            composable(Screen.Settings.route) {
                SettingsScreen(
                    onBack = { navController.popBackStack() },
                    onClearHistory = {
                        currentProfile = null
                        hasLoadedProfile = false
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
            
            composable(Screen.FingerprintGame.route) {
                FingerprintGameScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            
            composable(Screen.MatchGame.route) {
                MatchGameScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            
            composable(Screen.PasswordGenerator.route) {
                PasswordGeneratorScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            
            composable(Screen.Agreement.route) {
                AgreementScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

// Bottom Navigation Bar
@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigateToHome: () -> Unit,
    onNavigateToLearn: () -> Unit,
    onNavigateToGames: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    androidx.compose.material3.NavigationBar(
        containerColor = androidx.compose.ui.graphics.Color(0xFFffa333)
    ) {
        androidx.compose.material3.NavigationBarItem(
            icon = { androidx.compose.material.icons.Icons.Filled.Home },
            label = { androidx.compose.material3.Text("Inicio") },
            selected = currentRoute == "home",
            onClick = onNavigateToHome,
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = androidx.compose.ui.graphics.Color.White,
                unselectedIconColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.7f),
                selectedTextColor = androidx.compose.ui.graphics.Color.White,
                unselectedTextColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.7f),
                indicatorColor = androidx.compose.ui.graphics.Color(0xFFaf5700)
            )
        )
        androidx.compose.material3.NavigationBarItem(
            icon = { androidx.compose.material.icons.Icons.Filled.School },
            label = { androidx.compose.material3.Text("Aprende") },
            selected = currentRoute == "learn",
            onClick = onNavigateToLearn,
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = androidx.compose.ui.graphics.Color.White,
                unselectedIconColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.7f),
                selectedTextColor = androidx.compose.ui.graphics.Color.White,
                unselectedTextColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.7f),
                indicatorColor = androidx.compose.ui.graphics.Color(0xFFaf5700)
            )
        )
        androidx.compose.material3.NavigationBarItem(
            icon = { androidx.compose.material.icons.Icons.Filled.VideogameAsset },
            label = { androidx.compose.material3.Text("Juegos") },
            selected = currentRoute == "games",
            onClick = onNavigateToGames,
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = androidx.compose.ui.graphics.Color.White,
                unselectedIconColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.7f),
                selectedTextColor = androidx.compose.ui.graphics.Color.White,
                unselectedTextColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.7f),
                indicatorColor = androidx.compose.ui.graphics.Color(0xFFaf5700)
            )
        )
        androidx.compose.material3.NavigationBarItem(
            icon = { androidx.compose.material.icons.Icons.Filled.Settings },
            label = { androidx.compose.material3.Text("Ajustes") },
            selected = currentRoute == "settings",
            onClick = onNavigateToSettings,
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = androidx.compose.ui.graphics.Color.White,
                unselectedIconColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.7f),
                selectedTextColor = androidx.compose.ui.graphics.Color.White,
                unselectedTextColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.7f),
                indicatorColor = androidx.compose.ui.graphics.Color(0xFFaf5700)
            )
        )
    }
}
