package com.capstone.infentor.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.infentor.api.ApiConfig
import com.capstone.infentor.response.GlosariumResponse
import com.capstone.infentor.response.JobsItem

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class GlosariumViewModel : ViewModel() {
    private val _glosarium = MutableLiveData<List<JobsItem>>()
    val glosarium: LiveData<List<JobsItem>> get() = _glosarium
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        // Call the getGlosarium function when the ViewModel is initialized
        getGlosarium()
    }

    fun getGlosarium() {
        _isLoading.value = true
        val apiService = ApiConfig.getApiService()
        val call = apiService.getGlosarium()

        call.enqueue(object : Callback<GlosariumResponse> {
            override fun onResponse(
                call: Call<GlosariumResponse>,
                response: Response<GlosariumResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val glosariumResponse = response.body()
                    val glosariumList = glosariumResponse?.jobs
                    _glosarium.value = glosariumList!!
                } else {
                    _isLoading.value = false
                    Log.e("GlosariumViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GlosariumResponse>, t: Throwable) {
                Log.e("GlosariumViewModel", "onFailure: ${t.message.toString()}")
                t.printStackTrace()
            }
        })
    }
}