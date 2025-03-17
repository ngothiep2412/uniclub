package com.example.unihub.uniclub.presentation.home

import ProductModel

sealed interface HomeAction {
    data class AddToCart(val product: ProductModel) : HomeAction
}