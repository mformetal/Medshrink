package metal.diary.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import metal.diary.multiplatform.auth.AuthViewModel

const val AUTH_SCREEN_ROUTE = "auth"

@Composable
fun AuthScreen(navigation: NavController, viewModel: AuthViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        TextField(
//            onValueChange = { inputUsername ->
//            },
//            label = Text("Username")
//        )
//
//        TextField(
//            onValueChange = { inputPassword ->
//            },
//            label = Text("Password")
//        )
    }
}