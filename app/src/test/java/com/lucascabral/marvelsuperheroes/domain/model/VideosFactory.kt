package com.lucascabral.marvelsuperheroes.domain.model

import com.lucascabral.marvelsuperheroes.data.network.model.video.ItemId
import com.lucascabral.marvelsuperheroes.data.network.model.video.Snippet
import com.lucascabral.marvelsuperheroes.data.network.model.video.SnippetThumbnails
import com.lucascabral.marvelsuperheroes.data.network.model.video.ThumbnailsResolution

object VideosFactory {

    val videos = listOf(
        Video(
            id = ItemId("video", "1"),
            snippet = Snippet("04/06", "1", "Lokki", "Lokki Desc",
            thumbnails = SnippetThumbnails(default = ThumbnailsResolution("htpp://"),
                medium = ThumbnailsResolution("http://m"), high = ThumbnailsResolution("http://h")),
            channelTitle = "Marvel Brasil", publishTime = "04/06")
        ), Video(
            id = ItemId("video", "2"),
            snippet = Snippet("03/06", "1", "Iron Man", "IM Desc",
                thumbnails = SnippetThumbnails(default = ThumbnailsResolution("htpp://"),
                    medium = ThumbnailsResolution("http://m"), high = ThumbnailsResolution("http://h")),
                channelTitle = "Marvel Brasil", publishTime = "03/06")
        ), Video(
            id = ItemId("video", "3"),
            snippet = Snippet("02/06", "1", "Spider Man", "Spider Desc",
                thumbnails = SnippetThumbnails(default = ThumbnailsResolution("htpp://"),
                    medium = ThumbnailsResolution("http://m"), high = ThumbnailsResolution("http://h")),
                channelTitle = "Marvel Brasil", publishTime = "02/06")
        )
    )
}