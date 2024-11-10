// HomeSearchScreen.kt
package com.example.snapstory.fragment.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snapstory.R
import com.example.snapstory.components.icon.CloseIcon
import com.example.snapstory.components.icon.FindButton
import com.example.snapstory.components.icon.Heart
import com.example.snapstory.components.icon.HeartColor
import com.example.snapstory.components.icon.LeftButton
import com.example.snapstory.components.search.SearchViewModel
import com.example.snapstory.components.typography.Pretendard
import com.example.snapstory.data.TextDataViewModel
import com.example.snapstory.ui.theme.TextField_Gray

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeSearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel,
    textDataViewModel: TextDataViewModel
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Function to handle search action
    fun handleSearch() {
        val trimmedQuery = searchQuery.text.trim()
        if (trimmedQuery.isNotEmpty() && !searchViewModel.recentSearches.contains(trimmedQuery)) {
            searchViewModel.addSearchTerm(trimmedQuery)
        }
        focusManager.clearFocus()
        keyboardController?.hide()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(horizontal = 10.dp)
        ) {
            // Back button
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(24.dp)
            ) {
                LeftButton(modifier = Modifier.size(20.dp))
            }

            // Search field
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(34.dp)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = searchQuery,
                    onValueChange = { newValue ->
                        searchQuery = newValue
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (!focusState.isFocused) {
                                handleSearch()
                            }
                        },
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontFamily = Pretendard
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            handleSearch()
                        }
                    ),
                    decorationBox = { innerTextField ->
                        if (searchQuery.text.isEmpty()) {
                            Text(
                                text = "검색어를 입력해주세요.",
                                color = TextField_Gray,
                                fontFamily = Pretendard,
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    }
                )
            }

            // Search button
            IconButton(
                onClick = {
                    handleSearch()
                },
                modifier = Modifier.size(24.dp)
            ) {
                FindButton(modifier = Modifier.size(20.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display recent searches or search results
        if (searchQuery.text.isBlank()) {
            Text(
                text = "최근 검색어",
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = Pretendard
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(searchViewModel.recentSearches) { term ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = term, color = Color.Gray, fontFamily = Pretendard)
                        IconButton(onClick = { searchViewModel.removeSearchTerm(term) }) {
                            CloseIcon(
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                            )
                        }
                    }
                }
            }
        } else {
            val filteredResults = remember(searchQuery.text) {
                textDataViewModel.textData.value
                    .filter { it.first.contains(searchQuery.text, ignoreCase = true) }
                    .sortedByDescending { it.first.commonWordCount(searchQuery.text) }
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(filteredResults) { item ->
                    SearchResultCard(title = item.first, description = item.second)
                }
            }
        }
    }
}

@Composable
fun SearchResultCard(title: String, description: String) {
    var isHeartSelected by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Shadow effect for the card
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = 5.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.9f),
                            Color.Transparent
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        )

        // Actual card content
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = { isExpanded = !isExpanded },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        color = Color.Black,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = Pretendard
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = Pretendard
                    )
                }
                IconButton(
                    onClick = { isHeartSelected = !isHeartSelected }
                ) {
                    if (isHeartSelected) {
                        HeartColor(modifier = Modifier.size(24.dp))
                    } else {
                        Heart(modifier = Modifier.size(24.dp))
                    }
                }
            }
        }
    }
}

fun String.commonWordCount(query: String): Int {
    val queryWords = query.split(" ").map { it.lowercase() }
    return this.split(" ").count { it.lowercase() in queryWords }
}
