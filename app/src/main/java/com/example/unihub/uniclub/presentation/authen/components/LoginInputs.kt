package com.example.unihub.uniclub.presentation.authen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.unihub.R
import com.example.unihub.ui.theme.ComposeTheme
import com.example.unihub.ui.theme.UniclubTheme
import com.example.unihub.uniclub.presentation.authen.login.LoginState

@Composable
fun LoginInputs(
    loginState: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier,
) {
    val emailError = loginState.emailErrorResId?.let { stringResource(it) }
    val passwordError = loginState.passwordErrorResId?.let { stringResource(it) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        EmailTextField(
            modifier = modifier,
            value = loginState.email,
            onValueChange = onEmailChange,
            label = "Email",
            isError = emailError != null,
            errorText = emailError ?: "",
        )
        PasswordTextField(
            modifier = modifier,
            value = loginState.password,
            onValueChange = onPasswordChange,
            label = "Password",
            isError = passwordError != null,
            errorText = passwordError ?: ""
        )


        // Forgot Password
        Text(
            modifier = Modifier
                .padding(top = UniclubTheme.dimens.paddingSmall)
                .align(alignment = Alignment.End)
                .clickable {
                },
            text = stringResource(id = R.string.forgot_password),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium
        )


        LoadingButton(
            modifier = Modifier.padding(top = UniclubTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.login_button_text),
            onClick = onSubmit,
            isLoading = loginState.isLoading
        )
    }
}


@PreviewLightDark
@Composable
private fun PriceChangePreview() {
    ComposeTheme {
        LoginInputs(
            loginState = LoginState(),
            onEmailChange = {},
            onPasswordChange = {},
            onSubmit = {},
            modifier = Modifier
        )
    }
}