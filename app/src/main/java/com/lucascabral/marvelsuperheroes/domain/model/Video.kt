package com.lucascabral.marvelsuperheroes.domain.model

import com.lucascabral.marvelsuperheroes.data.network.model.video.SnippetThumbnails

data class Video(
    val title: String,
    val description: String,
    val thumbnails: SnippetThumbnails
)
