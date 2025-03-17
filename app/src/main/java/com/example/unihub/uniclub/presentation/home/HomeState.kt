package com.example.unihub.uniclub.presentation.home

import ProductModel
import androidx.compose.runtime.Immutable
import com.example.unihub.uniclub.domain.BrandModel
import com.example.unihub.uniclub.domain.CategoryModel

@Immutable
data class HomeState(
    val cartItems: List<String> = arrayListOf(),

    val products: List<ProductModel> = emptyList(),

//    val categories: List<CategoryModel> = emptyList(),

    val brand: List<BrandModel> = emptyList(),

    val isLoading: Boolean = false,

    val errorMessage: String = ""
)