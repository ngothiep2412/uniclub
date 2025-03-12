package com.example.unihub.uniclub.navigation.model

sealed class Screen(val route: String) {

    // Screen Routes
//    data object Welcome : Screen("welcome")
    data object SignUp : Screen("signup")
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object Profile : Screen("profile")
    data object MyCart : Screen("mycart")

    // Graph Routes
    data object AuthNav : Screen("AUTH_NAV_GRAPH")
    data object MainNav : Screen("MAIN_NAV_GRAPH")
    data object Root : Screen("ROOT")
}