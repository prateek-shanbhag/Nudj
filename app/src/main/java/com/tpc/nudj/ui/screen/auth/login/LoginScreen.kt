package com.tpc.nudj.ui.screen.auth.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tpc.nudj.ui.theme.NudjTheme
import com.tpc.nudj.viewmodels.auth.login.LoginViewModel


@Composable
fun LoginScreen(
    viewmodel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewmodel.loginUiState.collectAsState()
    LoginScreenLayout(
        uiState = uiState,
        onEmailInput = { email ->
            viewmodel.onEmailChange(email)
        },
        onPasswordInput = { pass ->
            viewmodel.onPasswordChange(pass)
        },
        onForgotPasswordClick = { /* Handle the forgot password click here */ },
        onLoginClick = { /* Handle the login click here */ },
        onGoogleClick = { /* Handle the Google sign-in here */ }
    )
}

@Composable
fun LoginScreenLayout(
    uiState: LoginUiState,
    onEmailInput: (String) -> Unit,
    onPasswordInput: (String) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    onGoogleClick: () -> Unit,
) {
    // Code the layout for login screen here with the ref design provided
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewLoginScreen() {
    NudjTheme {
        LoginScreenLayout(
            uiState = LoginUiState(),
            onEmailInput = {},
            onPasswordInput = {},
            onForgotPasswordClick = {},
            onLoginClick = {},
            onGoogleClick = {}
        )
    }
}
