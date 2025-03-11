package com.example.unihub.uniclub.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.unihub.uniclub.presentation.authen.LoginScreen
import com.example.unihub.uniclub.presentation.authen.LoginViewModel
import org.koin.androidx.compose.koinViewModel


@Preview
@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.NavGraph
        ) {
            navigation<Route.NavGraph>(
                startDestination = Route.LOGIN
            ) {
                composable<Route.LOGIN>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    // LoginScreen()
                    val viewModel = koinViewModel<LoginViewModel>()

                    LoginScreen(
                        viewModel = viewModel,
                        onSuccess = {
                            navController.navigate(Route.HOME) {
                                popUpTo(Route.LOGIN) { inclusive = true }
                            }
                        }
                    )

                }
            }
        }
    }
}