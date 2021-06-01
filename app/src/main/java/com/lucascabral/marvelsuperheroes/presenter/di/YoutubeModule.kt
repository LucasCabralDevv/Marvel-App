package com.lucascabral.marvelsuperheroes.presenter.di

import com.lucascabral.marvelsuperheroes.data.api.YoutubeService
import com.lucascabral.marvelsuperheroes.data.network.remote.YoutubeRetrofit
import com.lucascabral.marvelsuperheroes.data.repository.YoutubeVideoRepository
import com.lucascabral.marvelsuperheroes.data.repository.YoutubeVideoRepositoryImpl
import com.lucascabral.marvelsuperheroes.domain.GetVideos
import com.lucascabral.marvelsuperheroes.domain.GetVideosUseCase
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.MarvelYoutubeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val youtubeModule = module {

    single { YoutubeRetrofit.createService(YoutubeService::class.java) }

    factory<YoutubeVideoRepository> { YoutubeVideoRepositoryImpl(get()) }

    factory<GetVideosUseCase> { GetVideos(get()) }

    viewModel { MarvelYoutubeViewModel(get()) }
}