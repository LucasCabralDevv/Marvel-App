package com.lucascabral.marvelsuperheroes.presenter.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.databinding.ActivityMarvelYoutubeBinding
import com.lucascabral.marvelsuperheroes.presenter.adapter.YoutubeAdapter
import com.lucascabral.marvelsuperheroes.presenter.model.VideoUiModel
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.MarvelYoutubeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelYoutubeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarvelYoutubeBinding
    private lateinit var interstitialAd: InterstitialAd
    private val viewModelYoutube: MarvelYoutubeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarvelYoutubeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        initMobileAds()

        viewModelYoutube.videos.observe(this, { videosList ->
            if (videosList.isEmpty()) {
                showAlertDialogEmptyList()
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

    private fun initMobileAds() {
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        binding.apply {
            youtubeAdView.loadAd(adRequest)

            interstitialAd = InterstitialAd(this@MarvelYoutubeActivity)
            interstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
            interstitialAd.loadAd(AdRequest.Builder().build())

            youtubeAdView.adListener = object : AdListener() {

                override fun onAdLoaded() {
                }

                override fun onAdOpened() {
                    if (interstitialAd.isLoaded)
                        interstitialAd.show()
                    else
                        Toast.makeText(
                            this@MarvelYoutubeActivity,
                            "Error interstitial",
                            Toast.LENGTH_SHORT
                        ).show()
                }

                override fun onAdClosed() {
                }
            }
        }
    }

    private fun showAlertDialogEmptyList() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.alert_dialog_title_text)
            .setMessage(getString(R.string.alert_dialog_message_text))
            .setNegativeButton(getString(R.string.alert_dialog_empty_list_close_text)) { _, _ ->
                finish()
            }
            .setPositiveButton(getString(R.string.alert_dialog_empty_list_retry_text)) { _, _ ->
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
        setSupportActionBar(binding.toolbarYoutube)
        supportActionBar?.apply {
            title = getString(R.string.title_youtube_toolbar)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }
}