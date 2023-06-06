package com.capstone.infentor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.capstone.infentor.R
import com.capstone.infentor.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val dominantIntelligenceType = intent.getStringExtra(EXTRA_RESULT)
        Log.d("ResultActivity", "dominantIntelligenceType: $dominantIntelligenceType") // Logging untuk memeriksa nilai ekstra
        val headerText = "Mantap Deddy Kamu Memiliki Kecerdasan Pada  Bidang $dominantIntelligenceType"
        binding.tvHeader.text = headerText
    }

    companion object {
        const val EXTRA_RESULT = "extra_result"
    }
}
