package metal.medshrink.auth

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import metal.medshrink.auth.signin.SignInViewModel
import metal.medshrink.auth.signin.UserSignIn
import metal.medshrink.auth.signup.SignUpViewModel
import metal.medshrink.auth.signup.UserSignup
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun authModule(): Module = module {
    viewModel { AuthViewModel() }

    viewModel { SignInViewModel(userSignIn = UserSignIn(auth = Firebase.auth)) }

    viewModel { SignUpViewModel(userSignup = UserSignup(auth = Firebase.auth)) }
}
