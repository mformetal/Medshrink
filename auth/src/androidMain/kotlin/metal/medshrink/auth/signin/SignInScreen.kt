package metal.medshrink.auth.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import metal.medshrink.auth.R
import metal.medshrink.auth.signup.SIGN_UP_SCREEN_ROUTE
import org.koin.androidx.compose.koinViewModel

const val SIGN_IN_SCREEN_ROUTE = "auth_sign_in"

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = koinViewModel(),
    signInComplete: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val loginState by viewModel.uiState.collectAsState()
        if (loginState.signInComplete) {
            signInComplete()
        }

        Text(
            text = "SOME BLURB WILL GO HERE WITH SOME LOGO MAYBE IDK WHO CARES",
        )

        OutlinedTextField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = loginState.email,
            onValueChange = viewModel::email,
            label = { Text(text = stringResource(R.string.email_label_text)) }
        )

        OutlinedTextField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            visualTransformation = if (loginState.isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = loginState.password,
            onValueChange = viewModel::password,
            label = { Text(text = stringResource(R.string.password_label_text)) }
        )

        ClickableText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = AnnotatedString(stringResource(R.string.forgot_password_text)),
            style = TextStyle(textDecoration = TextDecoration.Underline),
            onClick = {
            }
        )

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = loginState.isLoginButtonEnabled,
            onClick = viewModel::login
        ) {
            Text(text = stringResource(R.string.login_button_text))
        }

        ClickableText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = AnnotatedString(stringResource(R.string.signup_now_text)),
            style = TextStyle(textDecoration = TextDecoration.Underline),
            onClick = {
                navController.navigate(SIGN_UP_SCREEN_ROUTE)
            }
        )
    }
}
