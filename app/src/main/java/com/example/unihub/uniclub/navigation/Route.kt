package com.example.unihub.uniclub.navigation

import kotlinx.serialization.Serializable


sealed interface Route {

    @Serializable
    data object NavGraph: Route

    @Serializable
    data object LOGIN: Route

    @Serializable
    data object HOME: Route
}