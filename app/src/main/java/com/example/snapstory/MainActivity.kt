package com.example.snapstory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.snapstory.components.search.SearchViewModel
import com.example.snapstory.data.GlobalViewModel
import com.example.snapstory.navigaiton.Navigation
import com.example.snapstory.ui.theme.SnapstoryTheme

// MainActivity.kt

import com.example.snapstory.data.TextDataViewModel

class MainActivity : ComponentActivity() {
    private val globalViewModel: GlobalViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val textDataViewModel: TextDataViewModel by viewModels() // Define TextDataViewModel here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnapstoryTheme {
                Navigation(
                    globalViewModel = globalViewModel,
                    searchViewModel = searchViewModel,
                    textDataViewModel = textDataViewModel // Pass it to Navigation
                )
            }
        }
    }
}



