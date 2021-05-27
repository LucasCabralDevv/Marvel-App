package com.lucascabral.marvelsuperheroes.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lucascabral.marvelsuperheroes.network.MarvelService
import com.lucascabral.marvelsuperheroes.network.model.Character
import com.lucascabral.marvelsuperheroes.repository.paging.CharacterPagingSource
import kotlinx.coroutines.flow.Flow

class CharactersRepository(private val apiService: MarvelService) {

     fun getResultStream(): Flow<PagingData<Character>> {
          return Pager(config = PagingConfig(pageSize = pageSize, maxSize = pageMax),
          pagingSourceFactory = { CharacterPagingSource(apiService) }
               ).flow
     }

     companion object {
          const val pageSize: Int = 20
          const val pageMax: Int = 200
     }
}