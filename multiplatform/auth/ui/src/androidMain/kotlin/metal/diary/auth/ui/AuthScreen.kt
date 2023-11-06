package metal.diary.auth.ui

import HOME_SCREEN_ROUTE
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(navController: NavController, viewModel: AuthViewModel = koinViewModel()) {
    val authState = viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val composableScope = rememberCoroutineScope()

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
            onClick = {
                composableScope.launch {
                    val response = viewModel.loginClicked()
                    if (response.successful) {
                        navController.navigate(HOME_SCREEN_ROUTE)
                    } else {
                        Toast.makeText(context, "Invalid auth", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        ) {
            Text("Login")
        }
    }
}
