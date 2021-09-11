package br.com.dio.trademapclone

import android.app.Application
import br.com.dio.trademapclone.di.daoModule
import br.com.dio.trademapclone.di.repositoryModule
import br.com.dio.trademapclone.di.serviceModule
import br.com.dio.trademapclone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule, serviceModule, repositoryModule, daoModule)
        }
    }
}