package com.lucascabral.marvelsuperheroes.domain

import com.lucascabral.marvelsuperheroes.data.repository.YoutubeVideoRepository
import com.lucascabral.marvelsuperheroes.domain.model.Video
import java.lang.Exception

class GetVideos(
    private val videoRepository: YoutubeVideoRepository
) : GetVideosUseCase {
    override suspend fun invoke(): List<Video> = try {
        videoRepository.getVideos()
    } catch (ex: Exception) {
        listOf()
    }
}

interface GetVideosUseCase {
    suspend operator fun invoke(): List<Video>
}