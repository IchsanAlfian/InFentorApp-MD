package com.capstone.infentor.api

import com.capstone.infentor.response.GlosariumResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("job")
    fun getGlosarium(): Call<GlosariumResponse>
}