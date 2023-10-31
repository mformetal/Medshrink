package metal.diary.multiplatform.auth

import org.koin.dsl.module

actual fun authModule() = module {
    single { AuthViewModel(get()) }
}