package com.lucascabral.marvelsuperheroes.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucascabral.marvelsuperheroes.data.network.model.comics.Comic
import com.lucascabral.marvelsuperheroes.domain.GetComicsByCharacterIdUseCase
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val getComicsByCharacterIdUseCase: GetComicsByCharacterIdUseCase
): ViewModel() {

    private val _comicsLiveData = MutableLiveData<List<Comic>>()
    val comics = _comicsLiveData as LiveData<List<Comic>>

    fun getComicsByCharacterId(characterId: Int) {
        viewModelScope.launch {
            val comics = getComicsByCharacterIdUseCase(characterId)
            _comicsLiveData.value = comics
        }
    }
}