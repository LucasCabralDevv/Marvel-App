package com.lucascabral.marvelsuperheroes.data.network.model.video

import com.lucascabral.marvelsuperheroes.domain.model.Video

data class VideoResponse(
    val id: ItemId,
    val snippet: Snippet
)

fun VideoResponse.toVideo() = Video(
    id = this.id,
    snippet = this.snippet
)
