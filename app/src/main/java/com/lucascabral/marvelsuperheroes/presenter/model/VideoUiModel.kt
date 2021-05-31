package com.lucascabral.marvelsuperheroes.presenter.model

import com.lucascabral.marvelsuperheroes.data.network.model.video.SnippetThumbnails
import com.lucascabral.marvelsuperheroes.domain.model.Video

data class VideoUiModel(
    val title: String,
    val description: String,
    val thumbnails: SnippetThumbnails
)

fun Video.toVideoUiModel() = VideoUiModel(
    title = this.title,
    description = this.description,
    thumbnails = this.thumbnails
)
