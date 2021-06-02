package com.lucascabral.marvelsuperheroes.presenter.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.lucascabral.marvelsuperheroes.BuildConfig
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.databinding.ActivityYoutubePlayerBinding

class YoutubePlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var playerBinding: ActivityYoutubePlayerBinding
    private lateinit var videoId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        playerBinding = ActivityYoutubePlayerBinding.inflate(layoutInflater)
        setContentView(playerBinding.root)

        videoId = intent.getStringExtra(VIDEO_ID).toString()
        playerBinding.youtubePlayer.initialize(BuildConfig.YOUTUBE_KEY, this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        restored: Boolean
    ) {
        player?.setFullscreen(true)
        player?.setShowFullscreenButton(false)
        player?.cueVideo(videoId)
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Toast.makeText(this, getString(R.string.player_youtube_error), Toast.LENGTH_LONG).show()
    }

    companion object {
        const val VIDEO_ID = "videoId"

        fun getStartIntent(context: Context, videoId: String): Intent {
            return Intent(context, YoutubePlayerActivity::class.java).apply {
                putExtra(VIDEO_ID, videoId)
            }
        }
    }
}