package com.lucascabral.marvelsuperheroes.presenter.view

import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.databinding.ActivityYoutubePlayerBinding

class YoutubePlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var playerBinding: ActivityYoutubePlayerBinding
    private lateinit var videoId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerBinding = ActivityYoutubePlayerBinding.inflate(layoutInflater)
        setContentView(playerBinding.root)

        val bundle: Bundle? = intent.extras
        bundle?.let {
            videoId = bundle.getString(VIDEO_ID).toString()
            playerBinding.youtubePlayer.initialize("AIzaSyBJZ_BdXITaCdT7Cyz4VY8CkZjn-89FNng", this)
        }
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        restored: Boolean
    ) {
        player?.setFullscreen(true)
        player?.setShowFullscreenButton(false)
        if (!restored) player?.cueVideo(videoId)
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Toast.makeText(this, getString(R.string.player_youtube_error), Toast.LENGTH_LONG).show()
    }

    companion object {
        const val VIDEO_ID = "videoId"
    }
}