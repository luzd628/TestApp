package com.dzulfikri.suitmediatestapp.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dzulfikri.suitmediatestapp.data.repository.UserRepository
import com.dzulfikri.suitmediatestapp.data.response.DataItem

class UserViewModel (private val userRepository: UserRepository) : ViewModel() {

    fun getUser():LiveData<PagingData<DataItem>>{
        return userRepository.getUser().cachedIn(viewModelScope)
    }

}