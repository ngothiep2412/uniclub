package com.example.unihub.uniclub.presentation.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.unihub.uniclub.navigation.graph.MainNavGraph
import com.example.unihub.uniclub.navigation.model.Screen
import com.example.unihub.uniclub.presentation.main.components.BottomNavigation

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    val currentDestination = navController
        .currentBackStackEntryAsState()
        .value?.destination?.route

    Scaffold(
        content = { innerPadding ->
            MainNavGraph(
                navController = navController,
                paddingValues = innerPadding
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentDestination !in listOf(
                    Screen.MyCart.route,
                    Screen.Login.route,
                    Screen.SignUp.route,
                )
            ) {
                BottomNavigation(
                    navController = navController,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    )
}