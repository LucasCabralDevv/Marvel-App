package com.lucascabral.marvelsuperheroes.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lucascabral.marvelsuperheroes.data.api.MarvelService
import com.lucascabral.marvelsuperheroes.data.network.model.character.Character

class CharacterPagingSource(private val apiService: MarvelService): PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {

            val page: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getCharacters(page)
            val nextPage: Int = response.data.offset + 1

            LoadResult.Page(data = response.data.results, prevKey = null, nextKey = nextPage)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}