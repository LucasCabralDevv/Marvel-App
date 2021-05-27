package com.lucascabral.marvelsuperheroes.di

import com.lucascabral.marvelsuperheroes.presenter.di.networkModule
import com.lucascabral.marvelsuperheroes.presenter.di.repositoryModule
import com.lucascabral.marvelsuperheroes.presenter.di.viewModelModule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModuleTest : KoinTest {

    @Test
    fun `Test Koin Modules`() {
        startKoin {
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }.checkModules()
        stopKoin()
    }
}