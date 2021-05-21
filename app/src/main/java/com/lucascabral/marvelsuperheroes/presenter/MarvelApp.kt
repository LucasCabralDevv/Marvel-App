package com.lucascabral.marvelsuperheroes.presenter

import android.app.Application
import com.lucascabral.marvelsuperheroes.presenter.di.networkModule
import com.lucascabral.marvelsuperheroes.presenter.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MarvelApp)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}