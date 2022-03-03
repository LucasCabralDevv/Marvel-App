package com.lucascabral.marvelsuperheroes.presenter.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
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
    private lateinit var rewardedVideoAd: RewardedVideoAd
    private val viewModelYoutube: MarvelYoutubeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarvelYoutubeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        //initInterstitialAd()

        viewModelYoutube.videos.observe(this) { videosList ->
            if (videosList.isEmpty()) {
                showAlertDialogEmptyList()
            } else {
                binding.youtubeProgressBar.visibility = View.GONE
                setupRecyclerView(videosList)
            }
        }
        viewModelYoutube.getVideos()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initMobileAds() {
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        binding.youtubeAdView.loadAd(adRequest)
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
                //initRewardedVideoAd()
                val intent = YoutubePlayerActivity.getStartIntent(
                    this@MarvelYoutubeActivity,
                    video.id.videoId
                )
                this@MarvelYoutubeActivity.startActivity(intent)
            }
        }
    }

    private fun initInterstitialAd() {
        binding.run {
            interstitialAd = InterstitialAd(this@MarvelYoutubeActivity)
            interstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
            interstitialAd.loadAd(AdRequest.Builder().build())
            interstitialAd.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    if (interstitialAd.isLoaded) {
                        interstitialAd.show()
                    } else {
                        Log.e("InterstitialAd_Error", "Error Interstitial Impl")
                    }
                }
                override fun onAdClosed() {
                    Log.d("InterstitialAd_Closed", "Interstitial Closed")
                }
            }
        }
    }

    private fun initRewardedVideoAd() {
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", AdRequest.Builder().build())
        rewardedVideoAd.rewardedVideoAdListener = object : RewardedVideoAdListener {
            override fun onRewardedVideoAdLoaded() {
                if (rewardedVideoAd.isLoaded) {
                    rewardedVideoAd.show()
                } else {
                    Log.e("Rewarded_Error", "Error Interstitial Impl")
                }
            }

            override fun onRewardedVideoAdOpened() {
                TODO("Not yet implemented")
            }

            override fun onRewardedVideoStarted() {
                TODO("Not yet implemented")
            }

            override fun onRewardedVideoAdClosed() {
                TODO("Not yet implemented")
            }

            override fun onRewarded(p0: RewardItem?) {
                TODO("Not yet implemented")
            }

            override fun onRewardedVideoAdLeftApplication() {
                TODO("Not yet implemented")
            }

            override fun onRewardedVideoAdFailedToLoad(p0: Int) {
                TODO("Not yet implemented")
            }

            override fun onRewardedVideoCompleted() {
                TODO("Not yet implemented")
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