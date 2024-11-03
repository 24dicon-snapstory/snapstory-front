package com.example.snapstory.components.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.snapstory.components.icon.AudioBookChosenIcon
import com.example.snapstory.components.icon.AudioBookIcon
import com.example.snapstory.components.icon.HomeChosenIcon
import com.example.snapstory.components.icon.HomeIcon
import com.example.snapstory.components.icon.Profile
import com.example.snapstory.components.icon.ProfileChosen
import com.example.snapstory.components.icon.Storage
import com.example.snapstory.components.icon.StorageChosen


data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: @Composable (Modifier) -> Unit,
    val selectedIcon: @Composable (Modifier) -> Unit,
    val label : String
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", "home", { HomeIcon(it) }, { HomeChosenIcon(it) }, "홈"),
        BottomNavItem("AudioBook", "audiobook", { AudioBookIcon(it) }, { AudioBookChosenIcon(it) }, "오디오북"),
        BottomNavItem("Storage", "storage", { Storage(it) }, { StorageChosen(it) }, "보관함"),
        BottomNavItem("Profile", "profile", { Profile(it) }, { ProfileChosen(it) }, "프로필"),
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    if (currentRoute == item.route) {
                        item.selectedIcon(Modifier)
                    } else {
                        item.icon(Modifier)
                    }
                },
                label = { Text(item.label) }, // 라벨 추가
                selected = currentRoute == item.route,
                onClick = {
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
            )
        }
    }
}