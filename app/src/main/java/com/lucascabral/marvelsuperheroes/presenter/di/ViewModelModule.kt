package com.lucascabral.marvelsuperheroes.presenter.di

import com.lucascabral.marvelsuperheroes.presenter.viewmodel.AllCharactersViewModel
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.MarvelYoutubeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelMarvelModule = module {

    viewModel { AllCharactersViewModel(get()) }
}

val viewModelYoutubeModule = module {

    single { MarvelYoutubeViewModel(get()) }
}