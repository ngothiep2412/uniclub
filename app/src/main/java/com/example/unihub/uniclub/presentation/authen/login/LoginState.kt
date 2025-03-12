package com.example.unihub.uniclub.presentation.authen.login

import androidx.compose.runtime.Immutable

@Immutable
data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailErrorResId: Int? = null,
    val passwordErrorResId: Int? = null
)
