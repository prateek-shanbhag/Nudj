package com.tpc.nudj.ui.screen.auth.register

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tpc.nudj.R
import com.tpc.nudj.ui.components.EmailTextField
import com.tpc.nudj.ui.components.LoadingIndicator
import com.tpc.nudj.ui.components.PasswordTextField
import com.tpc.nudj.ui.components.PrimaryButton
import com.tpc.nudj.ui.theme.NudjTheme
import com.tpc.nudj.viewmodels.auth.register.RegisterViewModel

val FigmaBg = Color(0xFFEBF1F5)
val FigmaDarkBlue = Color(0xFF0A3752)
val FigmaContainerGrey = Color(0xFFA2B5C6)
val FigmaLinkBlue = Color(0xFF4A90E2)

@Composable
fun RegisterScreen(
    viewmodel: RegisterViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
) {
    val uiState by viewmodel.registerUiState.collectAsStateWithLifecycle()

    LoadingIndicator(
        isLoading = uiState.isLoading
    ) {
        RegisterScreenLayout(
            uiState = uiState,
            onEmailInput = { email -> viewmodel.onEmailChange(email) },
            onPasswordInput = { pass -> viewmodel.onPasswordChange(pass) },
            onConfirmPasswordInput = { pass -> viewmodel.onConfirmPasswordChange(pass) },
            isPasswordVisible = uiState.isPasswordVisible,
            isConfirmPasswordVisible = uiState.isConfirmPasswordVisible,
            onPasswordVisibilityToggle = { viewmodel.onPasswordVisibilityToggle() },
            onConfirmPasswordVisibilityToggle = { viewmodel.onConfirmPasswordVisibilityToggle() },
            onSignUpClick = viewmodel::onRegisterClick,
            onGoogleClick = viewmodel::onGoogleClick,
            onBackClick = onNavigateBack,
            onLoginClick = onNavigateToLogin
        )
    }
}

@Composable
fun RegisterScreenLayout(
    uiState: RegisterUiState,
    onEmailInput: (String) -> Unit,
    onPasswordInput: (String) -> Unit,
    onConfirmPasswordInput: (String) -> Unit,
    onPasswordVisibilityToggle: () -> Unit,
    onConfirmPasswordVisibilityToggle: () -> Unit,
    isPasswordVisible : Boolean,
    isConfirmPasswordVisible: Boolean,
    onSignUpClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit // THIS WAS ADDED
) {

    var isStudentSelected by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FigmaBg)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.3f))

        Image(
            painter = painterResource(id = R.drawable.nudj_logo),
            contentDescription = "NUDJ Icon",
            modifier = Modifier.size(76.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Image(
            painter = painterResource(id = R.drawable.nudj),
            contentDescription = "NUDJ Text",
            modifier = Modifier.height(36.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        color = if (isStudentSelected) FigmaDarkBlue else Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        border = BorderStroke(1.dp, if (isStudentSelected) FigmaDarkBlue else Color.LightGray),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { isStudentSelected = true },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Student",
                    color = if (isStudentSelected) Color.White else FigmaDarkBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        color = if (!isStudentSelected) FigmaDarkBlue else Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        border = BorderStroke(1.dp, if (!isStudentSelected) FigmaDarkBlue else Color.LightGray),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { isStudentSelected = false },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Admin",
                    color = if (!isStudentSelected) Color.White else FigmaDarkBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "CREATE ACCOUNT",
            color = FigmaDarkBlue,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(14.dp))


        Surface(
            color = FigmaContainerGrey,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                EmailTextField(
                    value = uiState.email,
                    onValueChange = onEmailInput,
                    placeholder = "Institute mail id"
                )

                PasswordTextField(
                    value = uiState.password,
                    onValueChange = onPasswordInput,
                    label = "Password",
                    placeholder = "Password",
                    passwordVisible = isPasswordVisible,
                    onPasswordVisibilityToggle = onPasswordVisibilityToggle
                )

                PasswordTextField(
                    value = uiState.confirmPassword,
                    onValueChange = onConfirmPasswordInput,
                    label = "Confirm Password",
                    placeholder = "Confirm Password",
                    passwordVisible = isConfirmPasswordVisible,
                    onPasswordVisibilityToggle = onConfirmPasswordVisibilityToggle
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        PrimaryButton(
            text = "Create Account",
            onClick = onSignUpClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(110.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
            Text(
                text = "OR",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(
            onClick = onGoogleClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Continue with google",
                color = FigmaDarkBlue,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(0.6f))

        val annotatedText = buildAnnotatedString {
            append("Already have an account? ")
            withStyle(
                style = SpanStyle(
                    color = FigmaLinkBlue,
                    textDecoration = TextDecoration.Underline,
                    fontStyle = FontStyle.Italic
                )
            ) {
                append("Login")
            }
        }

        Text(
            text = annotatedText,
            modifier = Modifier
                .clickable { onLoginClick() }
                .padding(bottom = 24.dp),
            fontSize = 13.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewRegisterScreen() {
    NudjTheme {
        RegisterScreenLayout(
            uiState = RegisterUiState(),
            onEmailInput = {},
            onPasswordInput = {},
            onConfirmPasswordInput = {},
            onPasswordVisibilityToggle = {},
            onConfirmPasswordVisibilityToggle = {},
            isPasswordVisible = false,
            isConfirmPasswordVisible = false,
            onSignUpClick = {},
            onGoogleClick = {},
            onBackClick = {},
            onLoginClick = {}
        )
    }
}