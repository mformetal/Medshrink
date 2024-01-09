package metal.medshrink.android

import android.app.Application
import metal.medshrink.auth.authModule
import metal.medshrink.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MedshrinkApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        val appModule =
            module {
            }

        startKoin {
            androidLogger()
            androidContext(this@MedshrinkApplication)
            modules(appModule, authModule(), networkModule())
        }
    }
}
