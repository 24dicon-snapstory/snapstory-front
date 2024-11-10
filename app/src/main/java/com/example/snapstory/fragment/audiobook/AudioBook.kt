package com.example.snapstory.fragment.audiobook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snapstory.R
import com.example.snapstory.components.icon.AIIcon
import com.example.snapstory.components.icon.FindButton
import com.example.snapstory.components.icon.RightArrow
import com.example.snapstory.components.typography.Pretendard
import com.example.snapstory.data.AudioBookData
import com.example.snapstory.data.getRecentlyPlayedAudioBooks
import com.example.snapstory.data.getAIAudioBooks
import com.example.snapstory.data.getTodaySnapAudioBooks
import com.example.snapstory.navigaiton.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioBookScreen(navController: NavController) {
    val scrollState = rememberScrollState() // 스크롤 상태를 기억

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState) // 세로 스크롤 가능하게 설정
    ) {
        // Top header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "오디오북",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = Pretendard
            )
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                FindButton(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .clickable {
                            navController.navigate(Screen.AudioBookSearch.route)
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Recently Played Section
        SectionHeader(title = "최근 재생")
        AudioBookList(getRecentlyPlayedAudioBooks())

        Spacer(modifier = Modifier.height(16.dp))

        // AI Recommendation Section
        SectionHeader(title = "AI 추천", showAIIcon = true)
        AudioBookList(getAIAudioBooks())

        Spacer(modifier = Modifier.height(16.dp))

        // Today's Snap Section
        SectionHeader(title = "오늘의 스냅")
        AudioBookList(getTodaySnapAudioBooks())
    }
}

@Composable
fun SectionHeader(title: String, showAIIcon: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showAIIcon) {
            AIIcon(modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(2.dp))
        }

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontFamily = Pretendard
        )

        Spacer(modifier = Modifier.width(4.dp))

        IconButton(onClick = { /* Navigate to details screen or any action */ }) {
            RightArrow(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun AudioBookList(audioBooks: List<AudioBookData>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(audioBooks.size) { index ->
            AudioBookItem(audioBooks[index])
        }
    }
}

@Composable
fun AudioBookItem(audioBook: AudioBookData) {
    Image(
        painter = painterResource(id = audioBook.imageResId),
        contentDescription = "Audio Book Cover",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(120.dp) // Figma에 맞춰 109dp 너비 설정
            .height(180.dp) // Figma에 맞춰 144dp 높이 설정
            .clip(RoundedCornerShape(6.dp)) // 이미지의 모서리를 8.dp로 둥글게 설정
            .background(Color.Gray, shape = RoundedCornerShape(8.dp))
    )
}
