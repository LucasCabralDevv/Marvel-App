package com.lucascabral.marvelsuperheroes.presenter.di

import com.lucascabral.marvelsuperheroes.presenter.viewmodel.AllCharactersViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single { AllCharactersViewModel() }
}