package com.example.snapstory.fragment.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.snapstory.components.icon.FindButton
import com.example.snapstory.components.icon.Heart
import com.example.snapstory.components.icon.HeartColor
import com.example.snapstory.components.typography.Lemon
import com.example.snapstory.components.typography.Pretendard
import com.example.snapstory.data.GlobalViewModel
import com.example.snapstory.data.TextDataViewModel
import com.example.snapstory.navigaiton.Screen
import com.example.snapstory.ui.theme.Green

@Composable
fun HomeScreen(
    navController: NavController,
    globalViewModel: GlobalViewModel,
    textDataViewModel: TextDataViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        Log.d("HomeScreen", "Nickname: ${globalViewModel.nickname.value}")
        Log.d("HomeScreen", "Email: ${globalViewModel.email.value}")
        Log.d("HomeScreen", "Password: ${globalViewModel.password.value}")
        Log.d("HomeScreen", "Selected Interests: ${globalViewModel.selectedInterests}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp) // 전체 좌우 여백 조정
    ) {
        Spacer(modifier = Modifier.height(16.dp)) // 상단 여백 추가

        // Top header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp), // 상단 여백만 추가
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Snap Story",
                fontSize = 24.sp,
                fontFamily = Lemon,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            FindButton(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate(Screen.HomeSearch.route)
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Main content area with scrollable LazyColumn
        LazyColumn(
            modifier = Modifier.fillMaxWidth() // 전체 가로폭을 채우도록 설정
        ) {
            item {
                // User greeting box
                Box(
                    modifier = Modifier
                        .fillMaxWidth() // 박스가 가로 전체를 차지하도록 설정
                        .background(Green)
                        .padding(16.dp) // 박스 내부 여백
                ) {
                    Column {
                        Text(
                            text = "${globalViewModel.nickname.value}님",
                            fontSize = 18.sp,
                            fontFamily = Pretendard,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "이런 이야기는 어떠세요?",
                            fontSize = 14.sp,
                            fontFamily = Pretendard,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Story cards
            items(textDataViewModel.textData.value) { data ->
                StoryCard(title = data.first, description = data.second)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun StoryCard(title: String, description: String) {
    // Heart selected state and expanded state
    var isHeartSelected by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Shadow effect behind the card
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = 5.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.1f),
                            Color.Transparent
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        )

        // Actual Card content
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = { isExpanded = !isExpanded },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontFamily = Pretendard,
                        color = Color.Black,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(0.85f)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = description,
                        fontSize = 12.sp,
                        fontFamily = Pretendard,
                        color = Color.Gray,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }

                // Heart icon at the top-right corner
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (-8).dp, y = 8.dp)
                        .clickable(
                            onClick = { isHeartSelected = !isHeartSelected },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isHeartSelected) {
                        HeartColor(modifier = Modifier.size(22.dp))
                    } else {
                        Heart(modifier = Modifier.size(22.dp))
                    }
                }
            }
        }
    }
}
