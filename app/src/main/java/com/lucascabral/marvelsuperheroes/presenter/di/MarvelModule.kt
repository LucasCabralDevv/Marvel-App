package com.lucascabral.marvelsuperheroes.presenter.di

import com.lucascabral.marvelsuperheroes.data.api.MarvelService
import com.lucascabral.marvelsuperheroes.data.network.remote.MarvelRetrofit
import com.lucascabral.marvelsuperheroes.data.repository.CharactersRepository
import com.lucascabral.marvelsuperheroes.data.repository.ComicsRepository
import com.lucascabral.marvelsuperheroes.data.repository.ComicsRepositoryImpl
import com.lucascabral.marvelsuperheroes.domain.GetComicsByCharacterId
import com.lucascabral.marvelsuperheroes.domain.GetComicsByCharacterIdUseCase
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.AllCharactersViewModel
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val marvelModule = module {

    single { MarvelRetrofit.createService(MarvelService::class.java) }

    single { CharactersRepository(get()) }

    viewModel { AllCharactersViewModel(get()) }

    single<ComicsRepository> { ComicsRepositoryImpl(get()) }

    single<GetComicsByCharacterIdUseCase> { GetComicsByCharacterId(get()) }

    viewModel { CharacterDetailsViewModel(get()) }
}