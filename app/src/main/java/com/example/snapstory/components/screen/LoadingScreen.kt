package com.example.snapstory.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

//@Composable
//fun LoadingScreen(navController: NavController) {
//    var isDataLoaded by remember { mutableStateOf(false) }
//
//    LaunchedEffect(Unit) {
//        sharedViewModel.fetchHoroscope {
//            isDataLoaded = true
//        }
//    }
//
//    LaunchedEffect(isDataLoaded) {
//        if (isDataLoaded) {
//            delay(1000) // Optional delay for better UX
//            navController.navigate(Screen.Home.route) {
//                popUpTo(Screen.Starting.route) { inclusive = true }
//            }
//        }
//    }
//
//    val configuration = LocalConfiguration.current
//    val screenWidth = configuration.screenWidthDp.dp
//    val logoSize = screenWidth / 4
//
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        LoadingIcon(modifier = Modifier.size(logoSize))
//    }
//}