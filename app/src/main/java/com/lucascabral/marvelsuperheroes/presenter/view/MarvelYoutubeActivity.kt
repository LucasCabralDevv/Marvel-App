package com.lucascabral.marvelsuperheroes.presenter.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.databinding.ActivityMarvelYoutubeBinding
import com.lucascabral.marvelsuperheroes.presenter.adapter.YoutubeAdapter
import com.lucascabral.marvelsuperheroes.presenter.model.VideoUiModel
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.MarvelYoutubeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelYoutubeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarvelYoutubeBinding
    private val viewModelYoutube: MarvelYoutubeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarvelYoutubeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        viewModelYoutube.videos.observe(this, { videosList ->
            if (videosList.isEmpty()) {
                alertDialogEmptyList()
            } else {
                binding.youtubeProgressBar.visibility = View.GONE
                setupRecyclerView(videosList)
            }
        })
        viewModelYoutube.getVideos()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun alertDialogEmptyList() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.no_videos_found)
            .setMessage(getString(R.string.alertdialog_message))
            .setNegativeButton(getString(R.string.alertdialog_close_text)) { _, _ ->
                finish()
            }
            .setPositiveButton(getString(R.string.alertdialog_retry_text)) { _, _ ->
                viewModelYoutube.getVideos()
            }
            .setCancelable(false)
            .show()
    }

    private fun setupRecyclerView(videos: List<VideoUiModel>) {
        binding.youtubeRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MarvelYoutubeActivity)
            adapter = YoutubeAdapter(videos) { video ->
                val intent = YoutubePlayerActivity.getStartIntent(
                    this@MarvelYoutubeActivity,
                    video.id.videoId
                )
                this@MarvelYoutubeActivity.startActivity(intent)
            }
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            title = getString(R.string.title_youtube_toolbar)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }
}