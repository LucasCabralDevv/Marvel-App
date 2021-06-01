package com.lucascabral.marvelsuperheroes.presenter.di

import com.lucascabral.marvelsuperheroes.data.api.MarvelService
import com.lucascabral.marvelsuperheroes.data.network.remote.MarvelRetrofit
import com.lucascabral.marvelsuperheroes.data.repository.CharactersRepository
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.AllCharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val marvelModule = module {

    single { MarvelRetrofit.createService(MarvelService::class.java) }

    single { CharactersRepository(get()) }

    viewModel { AllCharactersViewModel(get()) }
}