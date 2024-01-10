package metal.medshrink.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import metal.medshrink.auth.signin.SignInScreen
import metal.medshrink.compose.resources.large_padding

const val ONBOARDING_ROUTE = "auth_onboarding"

@Composable
fun OnboardingScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            Text(
                text = "LOGO OR TUTORIAL OR SOMETHING GOES HERE",
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(large_padding),
            onClick = {
                navController.navigate(SignInScreen.route)
            }
        ) {
            Text(text = stringResource(R.string.get_started_text))
        }
    }
}
