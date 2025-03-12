package com.example.unihub.uniclub.presentation.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeState(
    val cartItems: List<String> = arrayListOf("item1", "item2", "item3"),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)