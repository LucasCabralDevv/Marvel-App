package com.lucascabral.marvelsuperheroes.presenter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.data.network.model.video.VideoResponse
import com.lucascabral.marvelsuperheroes.databinding.ActivityMarvelYoutubeBinding
import com.lucascabral.marvelsuperheroes.presenter.adapter.YoutubeAdapter
import com.lucascabral.marvelsuperheroes.presenter.model.VideoUiModel
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.MarvelYoutubeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelYoutubeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarvelYoutubeBinding
    private val viewModelYoutube: MarvelYoutubeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarvelYoutubeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        viewModelYoutube.videos.observe(this, Observer { videosList ->
            setupRecyclerView(videosList)
        })
        viewModelYoutube.getVideos()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setupRecyclerView(videos: List<VideoResponse>) {
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