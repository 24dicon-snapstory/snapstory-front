//package com.example.snapstory.components.navigation
//
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.navigation.NavController
//import androidx.navigation.compose.currentBackStackEntryAsState
//
//
//data class BottomNavItem(
//    val name: String,
//    val route: String,
//    val icon: @Composable (Modifier) -> Unit,
//    val selectedIcon: @Composable (Modifier) -> Unit,
//    val label : String
//)
//
//@Composable
//fun BottomNavigationBar(navController: NavController) {
//    val items = listOf(
//        BottomNavItem("Taro", "taro", { TaroIcon(it) }, { TaroChosenIcon(it) }, "타로"),
//        BottomNavItem("Home", "home", { HomeIcon(it) }, { HomeChosenIcon(it) }, "홈"),
//        BottomNavItem("Luck", "luck", { LuckIcon(it) }, { LuckChosenIcon(it) }, "운세")
//    )
//
//    NavigationBar(
//        containerColor = Color.White
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//
//        items.forEach { item ->
//            NavigationBarItem(
//                icon = {
//                    if (currentRoute == item.route) {
//                        item.selectedIcon(Modifier)
//                    } else {
//                        item.icon(Modifier)
//                    }
//                },
//                label = { Text(item.label) }, // 라벨 추가
//                selected = currentRoute == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//}