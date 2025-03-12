package com.example.unihub.uniclub.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.unihub.uniclub.presentation.authen.login.LoginScreen
import com.example.unihub.uniclub.presentation.authen.login.LoginViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


@Composable
fun RootNavigationGraph(
    navController: NavHostController,
) {

    var fakeLoading by remember { mutableStateOf(true) }
    // TODO: Check session token
    val session  = "";

    LaunchedEffect(Unit) {
        delay(1000)
        fakeLoading = false
    }

    if (!fakeLoading) {
        when {
            session.isEmpty() -> {
                NavHost(
                    navController = navController,
                    route = Screen.Root.route,
                    startDestination = Screen.AuthNav.route
                ) {

                }
            }
        }
    }

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