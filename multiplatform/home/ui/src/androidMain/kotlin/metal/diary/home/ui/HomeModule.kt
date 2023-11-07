package metal.diary.home.ui

import metal.diary.network.ApiClientQualifier
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun homeModule(): Module = module {
    viewModel { HomeViewModel(get(ApiClientQualifier)) }
}