package com.dzulfikri.suitmediatestapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dzulfikri.suitmediatestapp.data.api.ApiService
import com.dzulfikri.suitmediatestapp.data.response.DataItem
import java.io.IOException

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorposition ->
            val anchorPage = state.closestPageToPosition(anchorposition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {

        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val response = apiService.getUsersList(position, params.loadSize)

            val userList = response.body()?.data ?: emptyList()
            LoadResult.Page(
                data = userList,
                prevKey = if (position == 1) null else position -1,
                nextKey = if (userList.isEmpty()) null else position + 1
            )
        }catch (exception: Exception){
            LoadResult.Error(exception)
        }
    }
}