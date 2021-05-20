package com.lucascabral.marvelsuperheroes.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lucascabral.marvelsuperheroes.network.MarvelService
import com.lucascabral.marvelsuperheroes.network.model.Character
import com.lucascabral.marvelsuperheroes.repository.paging.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject


class AllCharactersViewModel: ViewModel(), KoinComponent {

     private val apiService: MarvelService by inject()

     fun getListData(): Flow<PagingData<Character>> {
          return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
          pagingSourceFactory = { CharacterPagingSource(apiService) }
               ).flow.cachedIn(viewModelScope)
     }
}