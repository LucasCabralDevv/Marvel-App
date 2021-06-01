package com.lucascabral.marvelsuperheroes.presenter.model

import com.lucascabral.marvelsuperheroes.data.network.model.video.SnippetThumbnails
import com.lucascabral.marvelsuperheroes.domain.model.Video

data class VideoUiModel(
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: SnippetThumbnails
)
