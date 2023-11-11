package metal.diary.addentry.ui

import metal.diary.network.ApiClientQualifier
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun addEntryModule(): Module = module {
    viewModel { AddEntryViewModel(get(ApiClientQualifier)) }
}
