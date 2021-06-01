package com.lucascabral.marvelsuperheroes.data.repository

import com.lucascabral.marvelsuperheroes.data.api.YoutubeService
import com.lucascabral.marvelsuperheroes.data.network.model.video.toVideo
import com.lucascabral.marvelsuperheroes.data.network.remote.Output
import com.lucascabral.marvelsuperheroes.data.network.remote.parseResponse
import com.lucascabral.marvelsuperheroes.domain.model.Video
import java.lang.Exception

class YoutubeVideoRepositoryImpl(
    private val service: YoutubeService
): YoutubeVideoRepository {

    override suspend fun getVideos(): List<Video> {

        return when (val result = service.getVideos().parseResponse()) {

            is Output.Success -> { result.value.items.map { videoResponse ->
                videoResponse.toVideo()
            } }
            is Output.Failure -> throw GetVideosException()
        }
    }
}

interface YoutubeVideoRepository {
    suspend fun getVideos(): List<Video>
}

class GetVideosException: Exception()