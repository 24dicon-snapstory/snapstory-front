//package com.example.snapstory.navigaiton
//
//import androidx.compose.animation.AnimatedContent
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.with
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import com.example.snapstory.components.navigation.BottomNavigationBar
//
//
//sealed class Screen(val route: String) {
//    object Logo : Screen("logo")
//    object Starting : Screen("starting")
//    object Loading : Screen("loading")
//    object Home : Screen("home")
//    object Luck : Screen("luck")
//    object Taro : Screen("taro")
//    object Past : Screen("past")
//    object Present : Screen("present")
//    object Future : Screen("future")
//}
//
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun Navigation() {
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//    val showNavigationBar = currentRoute == Screen.Past.route || currentRoute == Screen.Present.route || currentRoute == Screen.Future.route
//
//    Scaffold(
//        bottomBar = {
//            if (currentRoute != Screen.Logo.route && currentRoute != Screen.Starting.route && currentRoute != Screen.Loading.route) {
//                BottomNavigationBar(navController)
//            }
//        },
//        content = { innerPadding ->
//            AnimatedContent(
//                targetState = currentRoute,
//                transitionSpec = {
//                    fadeIn(animationSpec = tween(1000)) with fadeOut(animationSpec = tween(500))
//                }
//            ) { targetRoute ->
////                Row(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
////                    Box(modifier = Modifier.weight(1f)) {
////                        NavHost(navController = navController, startDestination = Screen.Logo.route) {
////                            composable(Screen.Logo.route) { LogoScreen(navController) }
////                            composable(Screen.Starting.route) { StartingScreen(navController, sharedViewModel) }
////                            composable(Screen.Loading.route) { LoadingScreen(navController, sharedViewModel) }
////                            composable(Screen.Home.route) { HomeScreen(navController, sharedViewModel) }
////                            composable(Screen.Luck.route) { LuckScreen(navController, sharedViewModel) }
////                            composable(Screen.Taro.route) { TaroScreen(navController, sharedViewModel) }
////                            composable(Screen.Past.route) { PastScreen(navController, sharedViewModel) }
////                            composable(Screen.Present.route) { PresentScreen(navController, sharedViewModel) }
////                            composable(Screen.Future.route) { FutureScreen(navController, sharedViewModel) }
////                        }
////                    }
////                    if (showNavigationBar) {
////                        LengthNavigationBar(navController = navController)
////                    }
////                }
//            }
//        }
//    )
//}
