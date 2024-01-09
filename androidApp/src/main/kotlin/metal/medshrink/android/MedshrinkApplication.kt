package metal.medshrink.android

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.auth
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import metal.medshrink.auth.authModule
import metal.medshrink.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MedshrinkApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        Firebase.auth.currentUser

        Napier.base(DebugAntilog())

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
