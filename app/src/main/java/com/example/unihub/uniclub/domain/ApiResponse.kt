package com.example.unihub.uniclub.domain

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseDTO<T>(
    @SerialName("statusCode")
    val statusCode: Int,

    @SerialName("message")
    val message: String?,

    @SerialName("data")
    val data: T?
)