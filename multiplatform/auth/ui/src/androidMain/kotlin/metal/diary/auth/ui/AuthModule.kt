package metal.diary.auth.ui

import metal.diary.network.ApiClientQualifier
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun authModule(): Module = module {
    viewModel { AuthViewModel(get(ApiClientQualifier), get()) }
}