package com.example.unihub.uniclub.domain

import com.example.unihub.core.domain.util.NetworkError
import com.example.unihub.core.domain.util.Result

interface UniclubDataSource {
    suspend fun login(email: String, password: String) : Result<String, NetworkError>
}