package metal.diary.listentries.ui

import metal.diary.network.ApiClientQualifier
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun listEntriesModule(): Module = module {
    viewModel { ListEntriesViewModel(get(ApiClientQualifier)) }
}