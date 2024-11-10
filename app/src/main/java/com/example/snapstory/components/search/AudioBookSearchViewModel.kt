package com.example.snapstory.components.search


import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.snapstory.R
import com.example.snapstory.data.AudioBookData

class AudioBookSearchViewModel : ViewModel() {

    // Mutable state lists to hold different categories of audiobooks
    var recentlyPlayedAudioBooks = mutableStateListOf<AudioBookData>()
    var aiRecommendations = mutableStateListOf<AudioBookData>()
    var todaySnapAudioBooks = mutableStateListOf<AudioBookData>()

    init {
        // Initialize with mock data
        loadMockData()
    }

    // Function to load mock data for testing before backend connection
    private fun loadMockData() {
        recentlyPlayedAudioBooks.addAll(
            listOf(
                AudioBookData("Audiobook 1", R.drawable.audiobook1),
                AudioBookData("Audiobook 2", R.drawable.audiobook2),
                AudioBookData("Audiobook 3", R.drawable.audiobook1),
                AudioBookData("Audiobook 4", R.drawable.audiobook2)
            )
        )

        aiRecommendations.addAll(
            listOf(
                AudioBookData("AI Recommended 1", R.drawable.audiobook1),
                AudioBookData("AI Recommended 2", R.drawable.audiobook2)
            )
        )

        todaySnapAudioBooks.addAll(
            listOf(
                AudioBookData("Today's Snap 1", R.drawable.audiobook1),
                AudioBookData("Today's Snap 2", R.drawable.audiobook2)
            )
        )
    }

    // TODO: Fetch data from backend API and update lists
    fun fetchAudioBooksFromBackend() {
        // Placeholder for backend connection logic
        // Use a coroutine to make API calls to fetch data and update state lists
        // For example:
        // viewModelScope.launch {
        //     val response = apiService.getRecentlyPlayedAudioBooks()
        //     recentlyPlayedAudioBooks.clear()
        //     recentlyPlayedAudioBooks.addAll(response)
        // }
    }
}