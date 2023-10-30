package metal.diary.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class DiaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        val appModule = module {
            
        }

        startKoin {
            androidLogger()
            androidContext(this@DiaryApplication)
            modules(appModule)
        }
    }
}
