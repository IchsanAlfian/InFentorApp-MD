package com.capstone.infentor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.infentor.databinding.ActivityPilganBinding

class PilganActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPilganBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilganBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupButton()

    }
    private fun setupButton() {
        val btnOption1 = binding.btnOption1
        val btnOption2 = binding.btnOption2
        val btnOption3 = binding.btnOption3
        val btnOption4 = binding.btnOption4
        val normalBackground = getColorStateList(R.color.primary)
        val clickedBackground = getColorStateList(R.color.secondary)

        btnOption1.setOnClickListener {
            btnOption1.backgroundTintList = clickedBackground
            btnOption2.backgroundTintList = normalBackground
            btnOption3.backgroundTintList = normalBackground
            btnOption4.backgroundTintList = normalBackground
        }

        btnOption2.setOnClickListener {
            btnOption1.backgroundTintList = normalBackground
            btnOption2.backgroundTintList = clickedBackground
            btnOption3.backgroundTintList = normalBackground
            btnOption4.backgroundTintList = normalBackground
        }

        btnOption3.setOnClickListener {
            btnOption1.backgroundTintList = normalBackground
            btnOption2.backgroundTintList = normalBackground
            btnOption3.backgroundTintList = clickedBackground
            btnOption4.backgroundTintList = normalBackground
        }

        btnOption4.setOnClickListener {
            btnOption1.backgroundTintList = normalBackground
            btnOption2.backgroundTintList = normalBackground
            btnOption3.backgroundTintList = normalBackground
            btnOption4.backgroundTintList = clickedBackground
        }
    }


}