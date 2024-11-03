package com.example.snapstory.fragment.home


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snapstory.components.icon.Logo
import com.example.snapstory.navigaiton.Screen
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavController) {
    var isDataLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(isDataLoaded) {
        if (isDataLoaded) {
            delay(1000) // Optional delay for better UX
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        }
    }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // 원본 화면 비율로 박스 크기 설정
    val boxWidth = screenWidth * (177f / 393f)
    val boxHeight = screenHeight * (181f / 852f)
    val logoHeight = screenHeight * (141f / 852f)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(boxWidth, boxHeight)
                .align(Alignment.TopStart)
        ) {
            // LogoIcon을 Box 크기만큼 채우도록 설정
            Logo(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(logoHeight)
            )

            Spacer(modifier = Modifier.height(8.dp)) // LogoIcon 아래 8dp 간격

            // Snap Story 텍스트
            Text(
                text = "Snap Story",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
