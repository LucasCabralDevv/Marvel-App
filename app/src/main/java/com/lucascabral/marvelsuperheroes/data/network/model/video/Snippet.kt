package com.lucascabral.marvelsuperheroes.data.network.model.video

data class Snippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: SnippetThumbnails,
    val channelTitle: String,
    val publishTime: String
)
