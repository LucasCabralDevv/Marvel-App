package com.lucascabral.marvelsuperheroes.data.network.model.comics

data class Comic(
    val characters: Characters,
    val creators: Creators,
    val dates: List<Date>,
    val description: String,
    val diamondCode: String,
    val digitalId: Int,
    val events: Events,
    val format: String,
    val id: Int,
    val images: List<Image>,
    val issueNumber: Int,
    val modified: String,
    val pageCount: Int,
    val prices: List<Price>,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val title: String,
    val urls: List<Url>
)