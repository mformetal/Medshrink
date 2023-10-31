package metal.diary.multiplatform.auth

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun authModule() = module {
    viewModel { AuthViewModel(get()) }
}