package com.lucascabral.marvelsuperheroes.data.network.model.comics

data class Characters(
    val available: Int,
    val collectionURI: String,
    val itemCharacters: List<ItemCharacter>,
    val returned: Int
)