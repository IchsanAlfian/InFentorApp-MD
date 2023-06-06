package com.capstone.infentor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.infentor.R
import com.capstone.infentor.databinding.ActivityEssayBinding
import com.capstone.infentor.databinding.ActivityMainBinding

class EssayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEssayBinding
    private lateinit var dominantIntelligenceType: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEssayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        dominantIntelligenceType = intent.getStringExtra(ResultActivity.EXTRA_RESULT) ?: ""

        binding.btnSubmit.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(ResultActivity.EXTRA_RESULT, dominantIntelligenceType)
            startActivity(intent)
        }
    }
}