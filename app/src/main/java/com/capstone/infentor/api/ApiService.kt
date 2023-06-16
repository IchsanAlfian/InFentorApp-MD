package com.capstone.infentor.api

import com.capstone.infentor.response.GlosariumResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("job")
    fun getGlosarium(): Call<GlosariumResponse>
}