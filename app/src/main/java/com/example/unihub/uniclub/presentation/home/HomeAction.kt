package com.example.unihub.uniclub.presentation.home

import com.example.unihub.uniclub.presentation.home.components.Product

sealed interface HomeAction {
    data class AddToCart(val product: Product) : HomeAction
}