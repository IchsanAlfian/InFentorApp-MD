package com.capstone.infentor.ui

import MlViewModel
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.infentor.databinding.ActivityEssayBinding


class EssayActivity : AppCompatActivity() {

    private lateinit var etAnswer: EditText
    private lateinit var binding: ActivityEssayBinding
    private lateinit var dominantIntelligenceType: String
    private lateinit var mlViewModel: MlViewModel
    private var careerRecommendation: String = ""

    private var backPressedOnce = false

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

        // Handle back button press
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backPressedOnce) {
                    navigateToMainActivity()
                } else {
                    backPressedOnce = true
                    Toast.makeText(this@EssayActivity, "Tekan sekali lagi untuk keluar dari kuis", Toast.LENGTH_SHORT).show()
                    resetBackPressedFlag()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun postAnswer() {
        val answer = etAnswer.text.toString()
        mlViewModel.requestCareerRecommendation(answer)
    }

    private fun observeCareerRecommendation() {
        mlViewModel.careerRecommendation.observe(this, { recommendation ->
            careerRecommendation = recommendation

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(ResultActivity.EXTRA_RESULT, dominantIntelligenceType)
            intent.putExtra(ResultActivity.EXTRA_CAREER_RECOMMENDATION, careerRecommendation)
            startActivity(intent)
            finish()
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.essayProgressBar.visibility = View.VISIBLE
        } else {
            binding.essayProgressBar.visibility = View.GONE
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun resetBackPressedFlag() {
        Handler().postDelayed({ backPressedOnce = false }, 2000)
    }
}
