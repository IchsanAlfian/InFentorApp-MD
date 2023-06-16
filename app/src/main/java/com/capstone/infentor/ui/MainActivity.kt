package com.capstone.infentor.ui

import MlViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.capstone.infentor.databinding.ActivityMainBinding
import com.capstone.infentor.viewmodel.GlosariumViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val glosariumViewModel: GlosariumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnPilgan.setOnClickListener {
            val intent = Intent(this, PilganActivity::class.java)
            startActivity(intent)
        }
        binding.ivGlosarium.setOnClickListener {
            glosariumViewModel.isLoading.observe(this) {
                showLoading(it)
            }
            val intent = Intent(this, GlosariumActivity::class.java)
            startActivity(intent)
        }
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.mainProgressBar.visibility = View.VISIBLE
        } else {
            binding.mainProgressBar.visibility = View.GONE
        }
    }
}
