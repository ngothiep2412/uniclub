package com.example.unihub.uniclub.presentation.home

import com.example.unihub.core.domain.util.NetworkError

sealed interface HomeEvent {
    data class Error(val error: NetworkError) : HomeEvent
}