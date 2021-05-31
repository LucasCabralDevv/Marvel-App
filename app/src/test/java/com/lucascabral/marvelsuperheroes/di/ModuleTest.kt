package com.lucascabral.marvelsuperheroes.di

import com.lucascabral.marvelsuperheroes.presenter.di.networkMarvelModule
import com.lucascabral.marvelsuperheroes.presenter.di.networkYoutubeModule
import com.lucascabral.marvelsuperheroes.presenter.di.repositoryMarvelModule
import com.lucascabral.marvelsuperheroes.presenter.di.viewModelMarvelModule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModuleTest : KoinTest {

    @Test
    fun `Test Koin Modules`() {
        startKoin {
            modules(listOf(networkMarvelModule, repositoryMarvelModule, viewModelMarvelModule))
        }.checkModules()
        stopKoin()
    }

    @Test
    fun `Test Koin Youtube Modules`() {
        startKoin {
            modules(listOf(networkYoutubeModule))
        }.checkModules()
        stopKoin()
    }
}