package com.example.unihub.uniclub.presentation.authen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unihub.R
import com.example.unihub.core.domain.util.onError
import com.example.unihub.core.domain.util.onSuccess
import com.example.unihub.uniclub.domain.UniclubDataSource
import com.example.unihub.uniclub.util.AppPreferencesDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val uniclubDataSource: UniclubDataSource,
    private val appPreferencesDataSource: AppPreferencesDataSource
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

    private fun saveToken(token: String) {
        viewModelScope.launch {
            appPreferencesDataSource.saveAuthToken(token)
        }
    }

    private fun login(email: String, password: String) {
        if (!validateInputs(email, password)) {
            return;
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            uniclubDataSource.login(email, password)
                .onSuccess { token ->
                    _state.update { it.copy(isLoading = false) }
                    saveToken(token);
                    _event.send(LoginEvent.Success)
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _event.send(LoginEvent.Error(error))
                }
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