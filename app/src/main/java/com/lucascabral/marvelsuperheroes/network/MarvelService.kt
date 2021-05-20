package com.lucascabral.marvelsuperheroes.network

import com.lucascabral.marvelsuperheroes.network.model.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(@Query("offset") offset: Int): MarvelResponse
}