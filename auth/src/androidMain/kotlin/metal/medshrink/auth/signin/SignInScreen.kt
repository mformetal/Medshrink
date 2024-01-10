package metal.medshrink.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import metal.medshrink.auth.R
import metal.medshrink.auth.signup.SignUpScreen
import metal.medshrink.compose.resources.medium_padding
import metal.medshrink.nav.Screen
import metal.medshrink.nav.ScreenMenuItem
import org.koin.androidx.compose.koinViewModel

object SignInScreen : Screen {

    override val route: String = "auth_sign_in"
    override val navigationIcon: ImageVector? = null
    override val navigationIconContentDescription: String? = null
    override val actions: List<ScreenMenuItem> = emptyList()
}

@Composable
fun SignInScreen(
    navController: NavController,
    modifier: Modifier,
    viewModel: SignInViewModel = koinViewModel(),
    signInComplete: () -> Unit
) {
    Column(
        modifier = modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        val loginState by viewModel.uiState.collectAsState()
        if (loginState.signInComplete) {
            signInComplete()
        }

        EmailField(modifier, loginState, viewModel)

        PasswordField(modifier, loginState, viewModel)

        Button(
            modifier = modifier.align(Alignment.CenterHorizontally).padding(vertical = medium_padding),
            enabled = loginState.isLoginButtonEnabled,
            onClick = viewModel::login,
        ) {
            Text(text = stringResource(R.string.login_button_text))
        }

        ClickableText(
            modifier = modifier.align(Alignment.CenterHorizontally).padding(vertical = medium_padding),
            text = AnnotatedString(stringResource(R.string.forgot_password_text)),
            style = TextStyle(textDecoration = TextDecoration.Underline),
            onClick = {
            }
        )

        OutlinedButton(
            modifier = modifier.align(Alignment.CenterHorizontally),
            onClick = {
                navController.navigate(SignUpScreen.route)
            }
        ) {
            Text(text = stringResource(R.string.signup_now_text))
        }
    }
}

@Composable
private fun ColumnScope.PasswordField(
    modifier: Modifier,
    loginState: SignInState,
    viewModel: SignInViewModel
) {
    OutlinedTextField(
        modifier = modifier.align(Alignment.CenterHorizontally),
        visualTransformation =
        if (loginState.isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = loginState.password,
        onValueChange = viewModel::password,
        label = { Text(text = stringResource(R.string.password_label_text)) }
    )
}

@Composable
private fun ColumnScope.EmailField(
    modifier: Modifier,
    loginState: SignInState,
    viewModel: SignInViewModel
) {
    OutlinedTextField(
        modifier = modifier.align(Alignment.CenterHorizontally),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        value = loginState.email,
        onValueChange = viewModel::email,
        label = { Text(text = stringResource(R.string.email_label_text)) }
    )
}
