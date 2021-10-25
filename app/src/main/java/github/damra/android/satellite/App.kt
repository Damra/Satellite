package github.damra.android.satellite

import android.app.Application
import github.damra.android.satellite.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            // Inject Android Context
            androidContext(this@App)

            // Modules here
            modules(
                listOf(
                    appModule
                )
            )
        }
    }

}