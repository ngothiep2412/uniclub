package com.example.unihub.uniclub.data.network

import ProductModel
import com.example.unihub.BuildConfig
import com.example.unihub.core.data.networking.constructUrl
import com.example.unihub.core.data.networking.safeCall
import com.example.unihub.core.domain.util.NetworkError
import com.example.unihub.core.domain.util.Result
import com.example.unihub.uniclub.domain.ApiResponseDTO
import com.example.unihub.uniclub.domain.BrandModel
import com.example.unihub.uniclub.domain.CategoryModel
import com.example.unihub.uniclub.domain.LoginRequest
import com.example.unihub.uniclub.domain.UniclubDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import timber.log.Timber

class RemoteUniclubDataSource(
    private val httpClient: HttpClient
) : UniclubDataSource {

    override suspend fun login(email: String, password: String): Result<String, NetworkError> {
        return safeCall<String> {
            Timber.d("network ${BuildConfig.BASE_URL}")
            httpClient.post(
                urlString = constructUrl("/authen")
            ) {
                setBody(LoginRequest(email, password))
            }
        }
    }


    override suspend fun getProducts(): Result<ApiResponseDTO<List<ProductModel>>, NetworkError> {
        return safeCall {
            Timber.d("network ${BuildConfig.BASE_URL}")
            httpClient.get(
                urlString = constructUrl("/product")
            )
        }
    }

    override suspend fun getCategories(): Result<ApiResponseDTO<List<CategoryModel>>, NetworkError> {
        return safeCall {
            Timber.d("network ${BuildConfig.BASE_URL}")
            httpClient.get(
                urlString = constructUrl("/category")
            )
        }
    }

    override suspend fun getBrands(): Result<ApiResponseDTO<List<BrandModel>>, NetworkError> {
        return safeCall {
            Timber.d("network ${BuildConfig.BASE_URL}")
            httpClient.get(
                urlString = constructUrl("/brand")
            )
        }
    }
}