package com.lucascabral.marvelsuperheroes.presenter.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
                Toast.makeText(this, getString(R.string.no_videos_found), Toast.LENGTH_LONG)
                    .show()
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

    private fun setupRecyclerView(videos: List<VideoUiModel>) {
        binding.youtubeRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MarvelYoutubeActivity)
            adapter = YoutubeAdapter(videos)
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