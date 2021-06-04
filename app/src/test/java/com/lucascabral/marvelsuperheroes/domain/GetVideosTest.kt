package com.lucascabral.marvelsuperheroes.domain

import com.lucascabral.marvelsuperheroes.data.repository.GetVideosException
import com.lucascabral.marvelsuperheroes.data.repository.YoutubeVideoRepository
import com.lucascabral.marvelsuperheroes.domain.model.VideosFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetVideosTest {

    private val repository = mockk<YoutubeVideoRepository>()
    private val getVideos = GetVideos(repository)

    @Test
    fun `when getVideos return a list with success`() = runBlocking {
        //Given
        coEvery { repository.getVideos() } returns VideosFactory.videos

        //When
        val result = getVideos()

        //Then
        assertEquals(result.size, VideosFactory.videos.size)
    }

    @Test
    fun `when getVideos return an exception`() = runBlocking {
        //Given
        coEvery { repository.getVideos() } throws GetVideosException()

        //When
        val result = getVideos()

        //Then
        assertEquals(result.size, 0)
    }
}