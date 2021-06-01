package com.lucascabral.marvelsuperheroes.data.network.model.video

import com.lucascabral.marvelsuperheroes.domain.model.Video

data class Snippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: SnippetThumbnails,
    val channelTitle: String,
    val publishTime: String
)
