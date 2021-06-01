package com.lucascabral.marvelsuperheroes.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucascabral.marvelsuperheroes.domain.GetVideosUseCase
import com.lucascabral.marvelsuperheroes.presenter.model.VideoUiModel
import com.lucascabral.marvelsuperheroes.presenter.model.toVideoUiModel
import kotlinx.coroutines.launch

class MarvelYoutubeViewModel(
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {

    private val _videosLiveData = MutableLiveData<List<VideoUiModel>>()
    val videos = _videosLiveData as LiveData<List<VideoUiModel>>

    fun getVideos() {
        viewModelScope.launch {
            val videoList = getVideosUseCase()
            _videosLiveData.value = videoList.map { video ->
                video.toVideoUiModel()
            }
        }
    }
}
