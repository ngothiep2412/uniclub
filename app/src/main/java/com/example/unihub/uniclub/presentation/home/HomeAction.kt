package com.example.unihub.uniclub.presentation.home

sealed interface HomeAction {
    data object AddToCart : HomeAction
}