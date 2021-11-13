package com.lucascabral.marvelsuperheroes.data.repository

import com.lucascabral.marvelsuperheroes.data.api.MarvelService
import com.lucascabral.marvelsuperheroes.data.network.model.comics.Comic
import com.lucascabral.marvelsuperheroes.data.network.remote.Output
import com.lucascabral.marvelsuperheroes.data.network.remote.parseResponse
import java.lang.Exception

class ComicsRepositoryImpl(
    private val service: MarvelService
): ComicsRepository {
    override suspend fun getComicsByCharacterId(characterId: Int): List<Comic> {
        return when (val result = service.getComicsByCharacterId(characterId).parseResponse()) {
            is Output.Success -> {
                result.value.data.results
            }
            is Output.Failure -> throw GetComicsException()
        }
    }
}

interface ComicsRepository {
    suspend fun getComicsByCharacterId(characterId: Int): List<Comic>
}

class GetComicsException: Exception()