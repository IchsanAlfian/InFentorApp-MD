package com.capstone.infentor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.capstone.infentor.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnHome.setOnClickListener {
            navigateToMainActivity()
        }

        val dominantIntelligenceType = intent.getStringExtra(EXTRA_RESULT)
        val careerRecommendation = intent.getStringExtra(EXTRA_CAREER_RECOMMENDATION)
        binding.tvJob.text = careerRecommendation
        Log.d("ResultActivity", "dominantIntelligenceType: $dominantIntelligenceType") // Logging untuk memeriksa nilai ekstra
        val headerText = "Mantap Deddy Kamu Memiliki Kecerdasan Pada Bidang $dominantIntelligenceType"
        binding.tvHeader.text = headerText

        // Handle back button press
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backPressedOnce) {
                    navigateToMainActivity()
                } else {
                    backPressedOnce = true
                    Toast.makeText(this@ResultActivity, "Tekan sekali lagi untuk keluar dari kuis", Toast.LENGTH_SHORT).show()
                    resetBackPressedFlag()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun resetBackPressedFlag() {
        binding.btnHome.postDelayed({ backPressedOnce = false }, 2000)
    }

    companion object {
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_CAREER_RECOMMENDATION = "extra_career_recommendation"
    }
}
