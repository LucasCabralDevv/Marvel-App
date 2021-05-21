package com.lucascabral.marvelsuperheroes.network.model

data class MarvelResponse(
    val code: Int,
    val status: String,
    val data: Data
)
