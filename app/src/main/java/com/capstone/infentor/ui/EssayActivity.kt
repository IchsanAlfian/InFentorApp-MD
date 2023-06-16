package com.capstone.infentor.ui

import MlViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.capstone.infentor.R
import com.capstone.infentor.databinding.ActivityEssayBinding
import com.capstone.infentor.databinding.ActivityMainBinding

class EssayActivity : AppCompatActivity() {

    private lateinit var etAnswer: EditText
    private lateinit var binding: ActivityEssayBinding
    private lateinit var dominantIntelligenceType: String
    private lateinit var mlViewModel: MlViewModel
    private var careerRecommendation: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEssayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        mlViewModel = ViewModelProvider(this).get(MlViewModel::class.java)

        dominantIntelligenceType = intent.getStringExtra(ResultActivity.EXTRA_RESULT) ?: ""
        etAnswer = binding.etAnswer
        binding.btnSubmit.setOnClickListener {
            postAnswer()
            mlViewModel.isLoading.observe(this) {
                showLoading(it)
            }


        }
            observeCareerRecommendation()
    }
    private fun postAnswer() {
        val answer = etAnswer.text.toString()
        mlViewModel.requestCareerRecommendation(answer)
    }


    private fun observeCareerRecommendation() {
        mlViewModel.careerRecommendation.observe(this, { recommendation ->
            careerRecommendation = recommendation
            val result = "$recommendation"
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(ResultActivity.EXTRA_RESULT, dominantIntelligenceType)
            intent.putExtra(ResultActivity.EXTRA_CAREER_RECOMMENDATION, careerRecommendation)
            startActivity(intent)

//            // Lakukan sesuatu dengan hasil yang diterima
//            binding.tvResult.text = result
        })
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.addStoryProgressBar.visibility = View.VISIBLE
        } else {
            binding.addStoryProgressBar.visibility = View.GONE
        }
    }
}