package com.example.unihub.uniclub.presentation.authen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.unihub.R
import com.example.unihub.ui.theme.ComposeTheme
import com.example.unihub.ui.theme.UniclubTheme
import com.example.unihub.uniclub.navigation.model.Screen
import com.example.unihub.uniclub.presentation.authen.components.LoginInputs
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.event.collect {
            when (it) {
                is LoginEvent.Error -> {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }
                is LoginEvent.Success -> {
                    navController.navigate(Screen.MainNav.route)
                    viewModel.saveToken()
                }
            }
        }
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(UniclubTheme.dimens.paddingLarge)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = UniclubTheme.dimens.paddingLarge)
                    .padding(bottom = UniclubTheme.dimens.paddingExtraLarge)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = UniclubTheme.dimens.paddingLarge)
                        .fillMaxWidth(),
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .padding(top = UniclubTheme.dimens.paddingSmall),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = R.drawable.ic_playstore)
                        .crossfade(enable = true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = stringResource(id = R.string.login_heading_text)
                )

                // Heading Login
                Text(
                    modifier = Modifier.padding(top = UniclubTheme.dimens.paddingLarge),
                    text = stringResource(id = R.string.login_heading_text)
                )


                LoginInputs(
                    loginState = state,
                    onEmailChange = {
                        viewModel.onAction(LoginAction.EmailChanged(it))
                    },
                    onPasswordChange = {
                        viewModel.onAction(LoginAction.PasswordChanged(it))
                    },
                    onSubmit =
                    {
                        viewModel.onAction(
                            LoginAction.Login(
                                state.email,
                                state.password
                            )
                        )
                    },

                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }
}


@PreviewLightDark
@Composable
private fun PriceChangePreview() {
    ComposeTheme {
        LoginScreen(
            viewModel = LoginViewModel(),
            navController = NavHostController(LocalContext.current)
        )
    }
}