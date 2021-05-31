package com.lucascabral.marvelsuperheroes.presenter.di

import com.lucascabral.marvelsuperheroes.domain.GetVideos
import com.lucascabral.marvelsuperheroes.domain.GetVideosUseCase
import org.koin.dsl.module

val domainYoutubeModule = module {

    single<GetVideosUseCase> { GetVideos(get()) }
}
