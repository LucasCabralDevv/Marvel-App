package com.lucascabral.marvelsuperheroes.network.model

data class Data(
    var offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)
