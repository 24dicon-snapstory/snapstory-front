package com.example.snapstory.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.snapstory.components.icon.*
import com.example.snapstory.ui.theme.Green
import com.example.snapstory.components.typography.Pretendard

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: @Composable () -> Unit,
    val selectedIcon: @Composable () -> Unit,
    val label: String
)

@Composable
fun CustomBottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", "home", { HomeIcon(Modifier.size(24.dp)) }, { HomeChosenIcon(Modifier.size(24.dp)) }, "홈"),
        BottomNavItem("AudioBook", "audiobook", { AudioBookIcon(Modifier.size(24.dp)) }, { AudioBookChosenIcon(Modifier.size(24.dp)) }, "오디오북"),
        BottomNavItem("Storage", "storage", { Storage(Modifier.size(24.dp)) }, { StorageChosenIcon(Modifier.size(24.dp)) }, "보관함"),
        BottomNavItem("Profile", "profile", { Profile(Modifier.size(24.dp)) }, { ProfileChosenIcon(Modifier.size(24.dp)) }, "프로필")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            val interactionSource = remember { MutableInteractionSource() }

            Column(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null // Removes ripple effect
                    ) {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (isSelected) {
                    item.selectedIcon()
                } else {
                    item.icon()
                }
                Spacer(modifier = Modifier.height(4.dp)) // Adjusts space between icon and text
                Text(
                    text = item.label,
                    color = if (isSelected) Green else Color.Black,
                    fontFamily = Pretendard,
                    fontSize = 12.sp
                )
            }
        }
    }
}
