package com.capstone.infentor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.infentor.R
import com.capstone.infentor.databinding.ActivityEssayBinding
import com.capstone.infentor.databinding.ActivityMainBinding

class EssayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEssayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEssayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}