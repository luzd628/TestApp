package com.dzulfikri.suitmediatestapp.injection

import android.content.Context
import com.dzulfikri.suitmediatestapp.data.api.ApiConfig
import com.dzulfikri.suitmediatestapp.data.repository.UserRepository

object Injection {

    fun provideUserRepository(context: Context):UserRepository{
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }

}