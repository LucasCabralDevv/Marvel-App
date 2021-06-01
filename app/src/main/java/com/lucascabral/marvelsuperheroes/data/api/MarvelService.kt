package com.lucascabral.marvelsuperheroes.data.api

import com.lucascabral.marvelsuperheroes.data.network.model.character.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(@Query("offset") offset: Int): MarvelResponse
}