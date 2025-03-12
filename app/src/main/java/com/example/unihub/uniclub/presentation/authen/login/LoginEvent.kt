package com.example.unihub.uniclub.presentation.authen.login

import com.example.unihub.core.domain.util.NetworkError

sealed interface LoginEvent {
    data class Error(val error: NetworkError) : LoginEvent
    data object Success: LoginEvent
}