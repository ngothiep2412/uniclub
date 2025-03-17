package com.example.unihub.uniclub.domain

import ProductModel
import com.example.unihub.core.domain.util.NetworkError
import com.example.unihub.core.domain.util.Result

interface UniclubRepository {
    suspend fun login(email: String, password: String): Result<String, NetworkError>

    suspend fun getProducts(): Result<List<ProductModel>, NetworkError>

    suspend fun getCategories(): Result<List<CategoryModel>, NetworkError>

    suspend fun getBrands(): Result<List<BrandModel>, NetworkError>
}