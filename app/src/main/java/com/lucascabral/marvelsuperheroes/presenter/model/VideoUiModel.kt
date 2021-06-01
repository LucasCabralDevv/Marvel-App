package com.lucascabral.marvelsuperheroes.presenter.model

import com.lucascabral.marvelsuperheroes.data.network.model.video.ItemId
import com.lucascabral.marvelsuperheroes.data.network.model.video.Snippet
import com.lucascabral.marvelsuperheroes.domain.model.Video

data class VideoUiModel(
    val id: ItemId,
    val snippet: Snippet
)

fun Video.toVideoUiModel() = VideoUiModel(
    id = this.id,
    snippet = this.snippet
)
