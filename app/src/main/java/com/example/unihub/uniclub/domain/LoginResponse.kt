package com.example.unihub.uniclub.domain

import kotlinx.serialization.Serializable


@Serializable
data class LoginResponseDTO(
    val token: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
)