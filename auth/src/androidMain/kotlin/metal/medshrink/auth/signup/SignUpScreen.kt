package metal.medshrink.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import metal.medshrink.auth.R
import metal.medshrink.nav.Screen
import org.koin.androidx.compose.koinViewModel

object SignUpScreen : Screen {

    override val route: String = "auth_sign_up"
    override fun navigationIcon(): ImageVector = Icons.Default.ArrowBack
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = koinViewModel(),
    signupComplete: () -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        val loginState by viewModel.uiState.collectAsState()
        if (loginState.signUpComplete) {
            signupComplete()
        }

        EmailField(loginState, viewModel)

        PasswordField(loginState, viewModel)

        Button(
            modifier = modifier.align(Alignment.CenterHorizontally),
            enabled = loginState.isLoginButtonEnabled,
            onClick = viewModel::signUp
        ) {
            Text(text = stringResource(R.string.signup_button_text))
        }

        AnimatedVisibility(loginState.signupError) {
            Row(
                modifier = modifier.border(
                    color = MaterialTheme.colorScheme.error,
                    width = 2.dp,
                    shape = RoundedCornerShape(5.dp)
                )
            ) {
                Icon(imageVector = Icons.Default.Warning, contentDescription = null)
                Text(text = stringResource(R.string.signup_generic_error))
            }
        }
    }
}

@Composable
private fun ColumnScope.EmailField(
    loginState: SignUpState,
    viewModel: SignUpViewModel
) {
    OutlinedTextField(
        modifier = Modifier.Companion.align(Alignment.CenterHorizontally),
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
                    modifier = Modifier.Companion.align(Alignment.CenterHorizontally),
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
}

@Composable
private fun ColumnScope.PasswordField(
    loginState: SignUpState,
    viewModel: SignUpViewModel
) {
    OutlinedTextField(
        modifier = Modifier.Companion.align(Alignment.CenterHorizontally),
        visualTransformation =
        if (loginState.isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
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
}
