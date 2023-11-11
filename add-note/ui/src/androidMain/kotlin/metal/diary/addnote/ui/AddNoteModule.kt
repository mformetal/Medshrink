package metal.diary.addnote.ui

import metal.diary.network.ApiClientQualifier
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun addNoteModule(): Module = module {
    viewModel { AddNoteViewModel(get(ApiClientQualifier)) }
}
