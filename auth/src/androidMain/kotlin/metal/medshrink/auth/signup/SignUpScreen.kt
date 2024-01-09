package metal.medshrink.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import metal.medshrink.auth.R
import org.koin.androidx.compose.koinViewModel

const val SIGN_UP_SCREEN_ROUTE = "auth_sign_up"

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = koinViewModel(), signupComplete: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val loginState by viewModel.uiState.collectAsState()
        if (loginState.signUpComplete) {
            signupComplete()
        }

        Text(
            text = "SOME BLURB WILL GO HERE WITH SOME LOGO MAYBE IDK WHO CARES",
        )

        OutlinedTextField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = loginState.email,
            isError = loginState.emailError != null,
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = {
                viewModel.validateEmail()
            }),
            supportingText = {
                loginState.emailError?.let { errorText ->
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = errorText,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (loginState.emailError != null) {
                    Icon(Icons.Filled.Warning, loginState.emailError, tint = MaterialTheme.colorScheme.error)
                }
            },
            onValueChange = viewModel::email,
            label = { Text(text = stringResource(R.string.email_label_text)) }
        )

        OutlinedTextField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            visualTransformation = if (loginState.isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = loginState.password,
            isError = loginState.passwordError != null,
            singleLine = true,
            supportingText = {
                loginState.passwordError?.let { errorText ->
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = errorText,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (loginState.passwordError != null) {
                    Icon(Icons.Filled.Warning, loginState.passwordError, tint = MaterialTheme.colorScheme.error)
                }
            },
            onValueChange = viewModel::password,
            label = { Text(text = stringResource(R.string.password_label_text)) }
        )

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = loginState.isLoginButtonEnabled,
            onClick = viewModel::signUp
        ) {
            Text(text = stringResource(R.string.signup_button_text))
        }
    }
}
