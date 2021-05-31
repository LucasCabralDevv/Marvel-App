package com.lucascabral.marvelsuperheroes.presenter.di

import com.lucascabral.marvelsuperheroes.data.repository.CharactersRepository
import com.lucascabral.marvelsuperheroes.data.repository.YoutubeVideoRepository
import com.lucascabral.marvelsuperheroes.data.repository.YoutubeVideoRepositoryImpl
import com.lucascabral.marvelsuperheroes.domain.GetVideos
import com.lucascabral.marvelsuperheroes.domain.GetVideosUseCase
import org.koin.dsl.module

val repositoryMarvelModule = module {

    single { CharactersRepository(get()) }
}

val repositoryYoutubeModule = module {

    single<YoutubeVideoRepository> { YoutubeVideoRepositoryImpl(get()) }
}