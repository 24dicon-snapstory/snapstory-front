package com.example.snapstory.components.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.snapstory.R

@Composable
fun Logo(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = null,
    )
}

@Composable
fun HomeIcon(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = null,
    )
}

@Composable
fun HomeChosenIcon(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = null,
    )
}