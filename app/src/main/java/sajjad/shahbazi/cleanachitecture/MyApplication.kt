package sajjad.shahbazi.cleanachitecture

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import sajjad.shahbazi.data.modules.remoteDataModule


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(mviProcessorModules, viewModelModules, remoteDataModule)
        }
    }
}