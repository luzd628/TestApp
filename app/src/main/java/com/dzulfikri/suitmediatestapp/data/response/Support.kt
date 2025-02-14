package com.dzulfikri.suitmediatestapp.data.response

import com.google.gson.annotations.SerializedName

data class Support(

	@field:SerializedName("text")
	val text: String,

	@field:SerializedName("url")
	val url: String
)