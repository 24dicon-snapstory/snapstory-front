package com.example.snapstory.data

import com.example.snapstory.R

data class AudioBookData(
    val title: String,
    val imageResId: Int
)

// Function to generate mock data for "최근 재생"
fun getRecentlyPlayedAudioBooks(): List<AudioBookData> {
    return listOf(
        AudioBookData("Audiobook 1", R.drawable.audiobook1),
        AudioBookData("Audiobook 2", R.drawable.audiobook2),
        AudioBookData("Audiobook 1", R.drawable.audiobook1),
        AudioBookData("Audiobook 2", R.drawable.audiobook2),
        AudioBookData("Audiobook 1", R.drawable.audiobook1),
        AudioBookData("Audiobook 2", R.drawable.audiobook2),
        AudioBookData("Audiobook 1", R.drawable.audiobook1),
        AudioBookData("Audiobook 2", R.drawable.audiobook2),
        AudioBookData("Audiobook 1", R.drawable.audiobook1),
        AudioBookData("Audiobook 2", R.drawable.audiobook2),
        AudioBookData("Audiobook 1", R.drawable.audiobook1),
        AudioBookData("Audiobook 2", R.drawable.audiobook2),
        AudioBookData("Audiobook 1", R.drawable.audiobook1),
        AudioBookData("Audiobook 2", R.drawable.audiobook2),
        AudioBookData("Audiobook 1", R.drawable.audiobook1),
        AudioBookData("Audiobook 2", R.drawable.audiobook2),
    )
}

// Function to generate mock data for "AI 추천"
fun getAIAudioBooks(): List<AudioBookData> {
    return listOf(
        AudioBookData("AI Recommended 1", R.drawable.audiobook1),
        AudioBookData("AI Recommended 2", R.drawable.audiobook2)
    )
}

// Function to generate mock data for "오늘의 스냅"
fun getTodaySnapAudioBooks(): List<AudioBookData> {
    return listOf(
        AudioBookData("Today's Snap 1", R.drawable.audiobook1),
        AudioBookData("Today's Snap 2", R.drawable.audiobook2)
    )
}