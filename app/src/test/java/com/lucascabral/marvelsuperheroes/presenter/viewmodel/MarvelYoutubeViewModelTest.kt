package com.lucascabral.marvelsuperheroes.presenter.viewmodel

import com.lucascabral.marvelsuperheroes.data.repository.GetVideosException
import com.lucascabral.marvelsuperheroes.domain.GetVideosUseCase
import com.lucascabral.marvelsuperheroes.domain.model.VideosFactory
import com.nhaarman.mockito_kotlin.isNull
import com.nhaarman.mockito_kotlin.notNull
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MarvelYoutubeViewModelTest {

    private val useCase = mockk<GetVideosUseCase>()
    private val viewModel = MarvelYoutubeViewModel(useCase)

    @Test
    fun `when useCase returns a list with success, viewModel videos return notNull`() = runBlocking {
            //Given
            coEvery { useCase.invoke() } returns VideosFactory.videos

            //When
            val result = viewModel.videos.value

            //Then
            assertEquals(result, notNull())
        }

    @Test
    fun `when useCase returns an exception, viewModel videos return isNull`() = runBlocking {
        //Given
        coEvery { useCase.invoke() } throws GetVideosException()

        //When
        val result = viewModel.videos.value

        //Then
        assertEquals(result, isNull())
    }
}