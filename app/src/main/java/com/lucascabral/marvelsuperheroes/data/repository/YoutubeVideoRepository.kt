package com.lucascabral.marvelsuperheroes.data.repository

import com.lucascabral.marvelsuperheroes.data.api.YoutubeService
import com.lucascabral.marvelsuperheroes.data.network.model.video.VideoResponse
import com.lucascabral.marvelsuperheroes.data.network.remote.Output
import com.lucascabral.marvelsuperheroes.data.network.remote.parseResponse
import java.lang.Exception

class YoutubeVideoRepositoryImpl(
    private val service: YoutubeService
): YoutubeVideoRepository {

    override suspend fun getVideos(): List<VideoResponse> {
        val result = service.getVideos().parseResponse()

        return when (result) {

            is Output.Success -> { result.value.items }
            is Output.Failure -> throw GetVideosException()
        }
    }
}

interface YoutubeVideoRepository {
    suspend fun getVideos(): List<VideoResponse>
}

class GetVideosException: Exception()