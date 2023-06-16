import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.infentor.ml.ApiClient
import com.capstone.infentor.ml.CareerRecommendationRequest
import com.capstone.infentor.ml.CareerRecommendationResponse
import com.capstone.infentor.ml.CareerRecommendationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MlViewModel : ViewModel() {
    private val _careerRecommendation = MutableLiveData<String>()
    val careerRecommendation: LiveData<String> get() = _careerRecommendation
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val service: CareerRecommendationService = ApiClient.retrofit.create(CareerRecommendationService::class.java)

    fun requestCareerRecommendation(inputText: String) {
        _isLoading.value = true
        val request = CareerRecommendationRequest(inputText)

        val call = service.predictCareer(request)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val recommendation = response.body()
                    recommendation?.let {
                        _careerRecommendation.value = recommendation!!
                        // Do something with the received result
                    }
                } else {
                    _isLoading.value = false
                    Log.e("MLViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("MLViewModel", "onFailure: ${t.message.toString()}")
                t.printStackTrace()
            }
        })
    }
}
