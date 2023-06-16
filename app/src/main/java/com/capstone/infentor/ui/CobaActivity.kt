package com.capstone.infentor.ui

import MlViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.capstone.infentor.R

import androidx.lifecycle.ViewModelProvider


class CobaActivity : AppCompatActivity() {
    private lateinit var etAnswer: EditText
    private lateinit var btnSubmit: Button
    private lateinit var mlViewModel: MlViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coba)

        etAnswer = findViewById(R.id.etAnswer)
        btnSubmit = findViewById(R.id.btnSubmit)

        mlViewModel = ViewModelProvider(this).get(MlViewModel::class.java)

        btnSubmit.setOnClickListener {
            postAnswer()
        }

        observeCareerRecommendation()
    }
    private fun postAnswer() {
        val answer = etAnswer.text.toString()
        mlViewModel.requestCareerRecommendation(answer)
    }


    private fun observeCareerRecommendation() {
        mlViewModel.careerRecommendation.observe(this, { recommendation ->
            val result = "Rekomendasi Karir anda adalah: $recommendation"

            // Lakukan sesuatu dengan hasil yang diterima
            val tvResult: TextView = findViewById(R.id.tvResult)
            tvResult.text = result
        })
    }
}
