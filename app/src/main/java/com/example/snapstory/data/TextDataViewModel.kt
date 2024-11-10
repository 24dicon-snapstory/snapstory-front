package com.example.snapstory.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TextDataViewModel : ViewModel() {

    // Simulated backend data as placeholder
    private val _textData = MutableStateFlow(
        listOf(
            "Title 1 Title 1 Title 1 Title 1 Title 1 Title 1 Title 1 Title 1 Title 1 Title 1 Title 1 Title 1 Title 1 " to "Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 Description for title 1 ",
            "Title 2" to "Description for title 2",
            "Title 3" to "Description for title 3"
        )
    )
    val textData: StateFlow<List<Pair<String, String>>> = _textData

    // Simulated function to fetch data from backend
    fun fetchDataFromBackend() {
        // TODO: Implement backend fetch logic here
        // Currently using hardcoded garbage data
        _textData.value = listOf(
            "Garbage Title 1" to "Random Description 1",
            "Garbage Title 2" to "Random Description 2",
            "Garbage Title 3" to "Random Description 3"
        )
    }
}