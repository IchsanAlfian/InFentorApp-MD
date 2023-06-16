package com.capstone.infentor.response

import com.google.gson.annotations.SerializedName

data class GlosariumResponse(

	@field:SerializedName("jobs")
	val jobs: List<JobsItem>,

	@field:SerializedName("message")
	val message: String
)

data class JobsItem(

	@field:SerializedName("nameJob")
	val nameJob: String,

	@field:SerializedName("jobId")
	val jobId: Int,

	@field:SerializedName("famous_example")
	val famousExample: String,

	@field:SerializedName("urlPictFamous")
	val urlPictFamous: String,

	@field:SerializedName("strong_in")
	val strongIn: String
)
