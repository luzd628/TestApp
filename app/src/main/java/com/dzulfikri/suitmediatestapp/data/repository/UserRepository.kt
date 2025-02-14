package com.dzulfikri.suitmediatestapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dzulfikri.suitmediatestapp.data.api.ApiService
import com.dzulfikri.suitmediatestapp.data.paging.UserPagingSource
import com.dzulfikri.suitmediatestapp.data.response.DataItem

class UserRepository(private val apiService: ApiService) {

    fun getUser() : LiveData<PagingData<DataItem>>{
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                initialLoadSize = 6
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData

    }

}