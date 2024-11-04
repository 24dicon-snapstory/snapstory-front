package com.example.snapstory.components.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snapstory.components.icon.Logo
import com.example.snapstory.components.icon.RightArrow
import com.example.snapstory.components.typography.Lemon
import com.example.snapstory.components.typography.Pretendard
import com.example.snapstory.navigaiton.Screen
import com.example.snapstory.ui.theme.Green
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combineTransform

@Composable
fun LoadingScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val boxWidth = screenWidth * (177f / 393f)
    val boxHeight = screenHeight * (181f / 852f)
    val logoHeight = screenHeight * (141f / 852f)

    // screenWidth, screenHeight, boxWidth, boxHeight, logoHeight를 Logcat에 출력
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.size(boxWidth, boxHeight)
        ) {
            // LogoIcon을 Box 크기만큼 채우도록 설정
            Logo(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(logoHeight)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Log.d("LogoCat", "Screen Width: ${screenWidth.value}dp, Screen Height: ${screenHeight.value}dp")
            Log.d("LogoCat", "Box Width: ${boxWidth.value}dp, Box Height: ${boxHeight.value}dp")
            Log.d("LogoCat", "Logo Height: ${logoHeight.value}dp")
            // Snap Story 텍스트
            Text(
                text = "Snap Story",
                fontSize = 24.sp,
                fontFamily = Lemon,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp) // 화면 가장자리와 버튼 간격
        ) {
            Button(
                onClick = {
                    // 버튼 클릭 시 동작
                    navController.navigate("login")

                },
                colors = ButtonDefaults.buttonColors(containerColor = Green),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "시작하기",
                        fontSize = 16.sp,
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp)) // 텍스트와 화살표 사이 간격
                    RightArrow(
                        modifier = Modifier.size(16.dp) // 화살표 크기 조절
                    )
                }
            }
        }
    }
}