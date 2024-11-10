package com.example.snapstory.components.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.snapstory.components.typography.Pretendard
import com.example.snapstory.data.GlobalViewModel
import com.example.snapstory.ui.theme.Green
import com.example.snapstory.ui.theme.TextField_Gray
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun Chose_Interests_Screen(
    navController: NavController,
    globalViewModel: GlobalViewModel = viewModel()
) {
    val interests = remember {
        listOf(
            "\uD83E\uDDEE 경제경영", "\uD83D\uDDA5\uFE0F 컴퓨터/모바일", "\uD83D\uDCDD 에세이 ", "\uD83C\uDF93 자기계발", "\uD83D\uDCD6 인문학 ",
            " \uD83E\uDDD1\u200D\uD83C\uDFA8 예술/대중문화 ", "\uD83D\uDCDA 소설/시/희곡 ", "\uD83D\uDE06만화", "\uD83D\uDD4D 동화", "\uD83D\uDD2E 종교/역학 ",
            "✨ 판타지", "\uD83E\uDD70 로맨스", "\uD83C\uDF7D\uFE0F 요리/살림", "👫 청소년", "\uD83D\uDEE0\uFE0F 기계", "\uD83C\uDFF3\uFE0F\u200D\uD83C\uDF08 외국어",
            "\uD83C\uDFDB\uFE0F 역사 ", "\uD83D\uDC76 육아"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "관심사 고르기",
            style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold, fontFamily = Pretendard),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "이야기 제작에 도움이 됩니다.",
            style = TextStyle(fontSize = 18.sp, color = TextField_Gray, fontFamily = Pretendard),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(70.dp))

        // 관심사 Chip들
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 12.dp
        ) {
            interests.forEach { interest ->
                SelectableChip(text = interest, globalViewModel = globalViewModel)
            }
        }

        Spacer(modifier = Modifier.weight(0.5f)) // 시작하기 버튼을 하단에 배치

        // 시작하기 버튼
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 4.dp), // 버튼 좌우 간격 조정
            colors = ButtonDefaults.buttonColors(containerColor = Green),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "시작하기",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black, fontFamily = Pretendard)
            )
        }
    }
}

@Composable
fun SelectableChip(text: String, globalViewModel: GlobalViewModel) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .background(
                color = if (isSelected) Green else Color(0xFFF5F5F5),
                shape = CircleShape
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                isSelected = !isSelected
                if (isSelected) {
                    globalViewModel.addInterest(text) // 선택 시 관심사 추가
                } else {
                    globalViewModel.removeInterest(text) // 해제 시 관심사 제거
                }
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                color = if (isSelected) Color.Black else Color.Black,
                fontFamily = Pretendard
            )
        )
    }
}
