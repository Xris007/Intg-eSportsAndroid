package pe.isil.esports

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pe.isil.esports.di.module.*

@ExperimentalCoroutinesApi
class KApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KApplication)
            modules(listOf(networkModule, championModule, godModule, heroModule, loginModule))
        }
    }

}