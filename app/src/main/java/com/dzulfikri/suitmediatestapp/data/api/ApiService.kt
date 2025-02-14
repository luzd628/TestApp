package com.dzulfikri.suitmediatestapp.data.api

import com.dzulfikri.suitmediatestapp.data.response.ListUserResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

 @GET ("users")
 suspend fun getUsersList(
    @Query("page") page: Int?,
    @Query("per_page") perPage: Int
 ): Response<ListUserResponse>

}