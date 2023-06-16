package com.capstone.infentor.ml

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface CareerRecommendationService {
    @POST("predict_text")
    fun predictCareer(@Body request: CareerRecommendationRequest): Call<String>
}