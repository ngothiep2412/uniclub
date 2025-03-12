package com.example.unihub.uniclub.navigation.graph

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.unihub.uniclub.navigation.model.Screen
import com.example.unihub.uniclub.presentation.home.HomeScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(),
) {
    NavHost(
        navController = navController,
        modifier = Modifier.padding(paddingValues),
        route = Screen.MainNav.route,
        startDestination = Screen.Home.route,
        enterTransition = { fadeIn(animationSpec = tween(100)) },
        exitTransition = { fadeOut(animationSpec = tween(100)) },
        popEnterTransition = { fadeIn(animationSpec = tween(100)) },
        popExitTransition = { fadeOut(animationSpec = tween(100)) }
    ) {
        authNav(navController)
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(navController)
        }
    }
}