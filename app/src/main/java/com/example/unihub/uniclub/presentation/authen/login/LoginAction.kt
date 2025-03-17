package com.example.unihub.uniclub.presentation.authen.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

sealed interface LoginAction {
    data class Login(val email: String, val password: String): LoginAction
    data class EmailChanged(val email: String) : LoginAction
    data class PasswordChanged(val password: String) : LoginAction

    data class ProcessGoogleSignInTask(val task: Task<GoogleSignInAccount>) : LoginAction
}