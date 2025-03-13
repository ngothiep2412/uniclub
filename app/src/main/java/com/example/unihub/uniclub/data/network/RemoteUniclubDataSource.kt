package com.example.unihub.uniclub.data.network

import com.example.unihub.BuildConfig
import com.example.unihub.core.data.networking.constructUrl
import com.example.unihub.core.data.networking.safeCall
import com.example.unihub.core.domain.util.NetworkError
import com.example.unihub.core.domain.util.Result
import com.example.unihub.uniclub.domain.LoginRequest
import com.example.unihub.uniclub.domain.UniclubDataSource
import io.ktor.client.HttpClient
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

}