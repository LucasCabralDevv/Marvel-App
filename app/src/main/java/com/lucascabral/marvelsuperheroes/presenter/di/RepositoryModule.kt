package com.lucascabral.marvelsuperheroes.presenter.di

import com.lucascabral.marvelsuperheroes.repository.CharactersRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { CharactersRepository() }
}