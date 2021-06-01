package com.lucascabral.marvelsuperheroes.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucascabral.marvelsuperheroes.data.network.model.video.VideoResponse
import com.lucascabral.marvelsuperheroes.domain.GetVideosUseCase
import kotlinx.coroutines.launch

class MarvelYoutubeViewModel(
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {

    private val _videosLiveData = MutableLiveData<List<VideoResponse>>()
    val videos = _videosLiveData as LiveData<List<VideoResponse>>

    fun getVideos() {
        viewModelScope.launch {
            val videoList = getVideosUseCase()
            _videosLiveData.value = videoList
        }
    }
}
