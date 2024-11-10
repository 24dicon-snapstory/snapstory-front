// Navigation.kt
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
import com.example.snapstory.components.navigation.CustomBottomNavigationBar
import com.example.snapstory.components.screen.Chose_Interests_Screen
import com.example.snapstory.components.screen.LoadingScreen
import com.example.snapstory.components.screen.LoginScreen
import com.example.snapstory.components.screen.PassWordScreen
import com.example.snapstory.components.screen.SignUpScreen
import com.example.snapstory.components.search.SearchViewModel
import com.example.snapstory.data.GlobalViewModel
import com.example.snapstory.data.TextDataViewModel
import com.example.snapstory.fragment.audiobook.AudioBookScreen
import com.example.snapstory.fragment.audiobook.AudioBookSearchScreen
import com.example.snapstory.fragment.home.HomeScreen
import com.example.snapstory.fragment.home.HomeSearchScreen

sealed class Screen(val route: String) {
    object Loading : Screen("loading")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object PassWord : Screen("password")
    object Home : Screen("home")
    object HomeSearch : Screen("homesearch")
    object Interests : Screen("interests")
    object AudioBook : Screen("audiobook")
    object AudioBookSearch : Screen("audiobooksearch")
    object Storage : Screen("storage")
    object Profile : Screen("profile")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    globalViewModel: GlobalViewModel,
    searchViewModel: SearchViewModel,
    textDataViewModel: TextDataViewModel // Pass textDataViewModel here
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(Screen.Home.route, Screen.AudioBook.route, Screen.Storage.route, Screen.Profile.route)) {
                CustomBottomNavigationBar(navController)
            }
        },
        content = { innerPadding ->
            AnimatedContent(
                targetState = currentRoute,
                transitionSpec = {
                    fadeIn(animationSpec = tween(1000)) with fadeOut(animationSpec = tween(500))
                }
            ) { targetRoute ->
                Row(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        NavHost(navController = navController, startDestination = Screen.Loading.route) {
                            composable(Screen.Loading.route) { LoadingScreen(navController) }
                            composable(Screen.Login.route) { LoginScreen(navController) }
                            composable(Screen.SignUp.route) { SignUpScreen(navController, globalViewModel) }
                            composable(Screen.PassWord.route) { PassWordScreen(navController) }
                            composable(Screen.Home.route) { HomeScreen(navController, globalViewModel, textDataViewModel) }
                            composable(Screen.Interests.route) { Chose_Interests_Screen(navController) }
                            composable(Screen.HomeSearch.route) {
                                HomeSearchScreen(
                                    navController = navController,
                                    searchViewModel = searchViewModel,
                                    textDataViewModel = textDataViewModel // Pass directly
                                )
                            }
                            composable(Screen.AudioBook.route) { AudioBookScreen(navController) }
                            composable(Screen.AudioBookSearch.route) { AudioBookSearchScreen(navController)  }
                            composable(Screen.Storage.route) { /* StorageScreen(navController) */ }
                            composable(Screen.Profile.route) { /* ProfileScreen(navController) */ }
                        }
                    }
                }
            }
        }
    )
}