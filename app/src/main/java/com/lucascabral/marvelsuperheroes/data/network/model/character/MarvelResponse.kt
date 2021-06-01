package com.lucascabral.marvelsuperheroes.data.network.model.character

data class MarvelResponse(
    val code: Int,
    val status: String,
    val data: Data
)
