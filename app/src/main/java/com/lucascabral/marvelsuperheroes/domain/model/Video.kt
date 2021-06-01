package com.lucascabral.marvelsuperheroes.domain.model

import com.lucascabral.marvelsuperheroes.data.network.model.video.ItemId
import com.lucascabral.marvelsuperheroes.data.network.model.video.Snippet

data class Video(
    val id: ItemId,
    val snippet: Snippet
)
