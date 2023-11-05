package metal.diary.multiplatform.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.koin.androidx.compose.koinViewModel

const val AUTH_SCREEN_ROUTE = "auth_screen_route"

@Composable
fun AuthScreen(viewModel: AuthViewModel = koinViewModel()) {
    val authState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = authState.value.username,
            onValueChange = viewModel::onUsernameInput,
            singleLine = true,
            label = { Text("Username") }
        )

        TextField(
            value = authState.value.password,
            visualTransformation = if (authState.value.isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = viewModel::onPasswordInput,
            singleLine = true,
            label = { Text("Password") }
        )

        Button(
            enabled = authState.value.isLoginButtonEnabled,
            onClick = viewModel::loginClicked
        ) {
            Text("Login")
        }
    }
}
