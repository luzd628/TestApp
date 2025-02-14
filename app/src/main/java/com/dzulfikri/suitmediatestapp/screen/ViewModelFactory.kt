package com.dzulfikri.suitmediatestapp.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzulfikri.suitmediatestapp.data.repository.UserRepository
import com.dzulfikri.suitmediatestapp.injection.Injection

class ViewModelFactory (private val user: UserRepository) : ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UserViewModel::class.java) ->{
                UserViewModel(user) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : ViewModelFactory? = null

        fun getInstance (context: Context):ViewModelFactory{
            return  INSTANCE
                ?: synchronized(this){
                    ViewModelFactory(
                        Injection.provideUserRepository(context)
                    )
                }
        }
    }

}