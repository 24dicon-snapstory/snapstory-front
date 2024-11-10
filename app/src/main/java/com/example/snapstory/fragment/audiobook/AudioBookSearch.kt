package com.example.snapstory.fragment.audiobook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snapstory.components.icon.FindButton
import com.example.snapstory.components.icon.LeftButton
import com.example.snapstory.components.typography.Pretendard
import com.example.snapstory.data.AudioBookData
import com.example.snapstory.data.getRecentlyPlayedAudioBooks
import com.example.snapstory.data.getAIAudioBooks
import com.example.snapstory.data.getTodaySnapAudioBooks
import com.example.snapstory.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AudioBookSearchScreen(
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Filter audiobooks based on search query
    val allAudioBooks = getRecentlyPlayedAudioBooks() + getAIAudioBooks() + getTodaySnapAudioBooks()
    val filteredAudioBooks = allAudioBooks.filter {
        it.title.contains(searchQuery.text, ignoreCase = true)
    }

    // Function to handle search action
    fun handleSearch() {
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
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontFamily = Pretendard
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = { handleSearch() }
                    ),
                    decorationBox = { innerTextField ->
                        if (searchQuery.text.isEmpty()) {
                            Text(
                                text = "검색어를 입력해주세요.",
                                color = Color.Gray,
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
                onClick = { handleSearch() },
                modifier = Modifier.size(24.dp)
            ) {
                FindButton(modifier = Modifier.size(20.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display search results
        if (filteredAudioBooks.isEmpty()) {
            Text(
                text = "검색 결과가 없습니다.",
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = Pretendard,
                color = Color.Gray
            )
        } else {
            // Grid layout for displaying audiobooks in three columns
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredAudioBooks) { audioBook ->
                    AudioBookResultCard(audioBook = audioBook)
                }
            }
        }
    }
}

@Composable
fun AudioBookResultCard(audioBook: AudioBookData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = audioBook.imageResId),
            contentDescription = null,
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(6.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = audioBook.title,
            fontSize = 14.sp,
            color = Color.Black,
            fontFamily = Pretendard,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}