package com.example.snapstory.components.search

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    // List of recent search terms
    val recentSearches = mutableStateListOf("김태현 바보", "김태현 같아얏어짐 ㅋㅋ")

    // List of all available items to search from
    private val allItems = listOf("Item 1", "김태현 바보", "김태현 같아얏어짐 ㅋㅋ", "Another Item")

    // Filtered search results based on input query
    val searchResults = mutableStateOf(allItems)

    // Update recent search terms
    fun addSearchTerm(term: String) {
        if (term.isNotBlank() && term !in recentSearches) {
            recentSearches.add(0, term)
            if (recentSearches.size > 5) {
                recentSearches.removeLast() // Limit recent searches to 5 items
            }
        }
    }

//    // Filter items based on search query
//    fun search(query: String) {
//        searchResults.value = if (query.isBlank()) {
//            allItems
//        } else {
//            allItems.filter { it.contains(query, ignoreCase = true) }
//        }
//    }

    fun removeSearchTerm(term: String) {
        recentSearches.remove(term)
    }
}