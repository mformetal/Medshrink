package metal.medshrink.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import metal.medshrink.compose.resources.medium_padding
import org.koin.androidx.compose.koinViewModel

const val LOGIN_SCREEN_ROUTE = "auth_login"

@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {
        val loginState by viewModel.uiState.collectAsState()
        val centerHorizontalAlignment = Modifier.align(Alignment.CenterHorizontally)

        Text(
            text = "SOME BLURB WILL GO HERE WITH SOME LOGO MAYBE IDK WHO CARES",
        )

        OutlinedTextField(
            modifier = centerHorizontalAlignment,
            value = loginState.username,
            onValueChange = viewModel::username,
            label = { Text(text = stringResource(R.string.username_label_text)) }
        )

        OutlinedTextField(
            modifier = centerHorizontalAlignment,
            value = loginState.password,
            onValueChange = viewModel::password,
            label = { Text(text = stringResource(R.string.password_label_text)) }
        )

        Text(
            modifier = centerHorizontalAlignment,
            text = stringResource(R.string.forgot_password_text),
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )

        Button(
            modifier = centerHorizontalAlignment,
            onClick = {

            }) {
            Text(text = stringResource(R.string.login_button_text))
        }

        Button(
            modifier = centerHorizontalAlignment,
            onClick = {

            }) {
            Text(text = stringResource(R.string.login_with_google_button_text))
        }
    }
}
