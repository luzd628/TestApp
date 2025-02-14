package com.dzulfikri.suitmediatestapp.data.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(

	@field:SerializedName("per_page")
	val perPage: Int,

	val total: Int,

	val data: List<DataItem>,

	@field:SerializedName("page")
	val page: Int,

	val totalPages: Int,

)