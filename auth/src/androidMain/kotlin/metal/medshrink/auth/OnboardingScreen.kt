package metal.medshrink.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import metal.medshrink.compose.resources.medium_padding

const val ONBOARDING_ROUTE = "auth_onboarding"

@Composable
fun OnboardingScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min), verticalAlignment = Alignment.Bottom) {
            Button(
                modifier = Modifier.weight(1f).padding(horizontal = medium_padding),
                onClick = {
                    navController.navigate(LOGIN_SCREEN_ROUTE)
                }) {
                Text(text = stringResource(R.string.login_button_text))
            }

            Divider(modifier = Modifier.fillMaxHeight(fraction = .8f).width(1.dp).align(Alignment.CenterVertically))

            Button(
                modifier = Modifier.weight(1f).padding(horizontal = medium_padding),
                onClick = {
                    navController.navigate(REGISTER_SCREEN_ROUTE)
                }) {
                Text(text = stringResource(R.string.register_button_text))
            }
        }
    }
}