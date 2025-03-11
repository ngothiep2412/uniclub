package com.example.unihub.uniclub.presentation.authen

import com.example.unihub.core.domain.util.NetworkError

sealed interface LoginEvent {
    data class Error(val error: NetworkError) : LoginEvent
}