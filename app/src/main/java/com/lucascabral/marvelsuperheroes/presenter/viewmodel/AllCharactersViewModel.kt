package com.lucascabral.marvelsuperheroes.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lucascabral.marvelsuperheroes.data.network.model.character.Character
import com.lucascabral.marvelsuperheroes.data.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class AllCharactersViewModel(
    private val repository: CharactersRepository
) : ViewModel() {

    fun getListData(): Flow<PagingData<Character>> {
        return repository.getResultStream()
            .cachedIn(viewModelScope)
    }
}