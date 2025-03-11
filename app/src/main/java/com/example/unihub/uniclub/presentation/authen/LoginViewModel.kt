package com.example.unihub.uniclub.presentation.authen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unihub.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(

) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _event = Channel<LoginEvent>()
    val event = _event.receiveAsFlow()


    private val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.Login -> login(action.email, action.password);
            is LoginAction.EmailChanged -> updatedEmail(action.email)
            is LoginAction.PasswordChanged -> updatedPassword(action.password)
        }
    }

    private fun updatedEmail(email: String) {
        Log.d("LoginViewModel", "updatedEmail: $email")
        _state.update {
            it.copy(
                email = email,
                emailErrorResId = null,
            )
        }
    }

    private fun updatedPassword(password: String) {
        Log.d("LoginViewModel", "updatePassword: $password")
        _state.update {
            it.copy(
                password = password,
                passwordErrorResId = null,
            )
        }
    }

    private fun login(email: String, password: String) {
        if (!validateInputs(email, password)) {
            return;
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        var isValid = true

        if (email.isBlank()) {

            _state.update { it.copy(emailErrorResId = R.string.email_empty) }
            isValid = false

        }

        if (!emailRegex.matches(email)) {
            _state.update { it.copy(emailErrorResId = R.string.email_invalid) }
            isValid = false

        }

        if (password.isBlank()) {
            _state.update { it.copy(passwordErrorResId = R.string.password_empty) }
            isValid = false;
        }

        if (password.length < 6) {
            _state.update { it.copy(passwordErrorResId = R.string.password_short) }
            isValid = false;
        }

        return isValid;
    }
}