package com.example.unihub.uniclub.presentation.authen.login

sealed interface LoginAction {
    data class Login(val email: String, val password: String): LoginAction
    data class EmailChanged(val email: String) : LoginAction
    data class PasswordChanged(val password: String) : LoginAction
}