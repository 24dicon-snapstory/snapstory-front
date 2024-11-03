package com.example.snapstory.navigaiton

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.snapstory.components.navigation.BottomNavigationBar
import com.example.snapstory.components.screen.LoadingScreen
import com.example.snapstory.fragment.home.HomeScreen


sealed class Screen(val route: String) {
    object Loading : Screen("loading")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object AudioBook : Screen("audiobook")
    object Storage : Screen("storage")
    object Chose : Screen("chose")
    object Profile : Screen("profile")
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
//        bottomBar = {
//            if (currentRoute != Screen.Logo.route && currentRoute != Screen.Starting.route && currentRoute != Screen.Loading.route) {
//                BottomNavigationBar(navController)
//            }
//        },
        content = { innerPadding ->
            AnimatedContent(
                targetState = currentRoute,
                transitionSpec = {
                    fadeIn(animationSpec = tween(1000)) with fadeOut(animationSpec = tween(500))
                }
            ) { targetRoute ->
                Row(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f)) {
                        NavHost(navController = navController, startDestination = Screen.Loading.route) {
                            composable(Screen.Loading.route) { LoadingScreen(navController) }

                            composable(Screen.Home.route) { HomeScreen(navController) }

                        }
                    }
                }
            }
        }
    )
}
