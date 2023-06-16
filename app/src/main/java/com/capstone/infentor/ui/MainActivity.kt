package com.capstone.infentor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.capstone.infentor.databinding.ActivityMainBinding
import com.capstone.infentor.viewmodel.GlosariumViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val glosariumViewModel: GlosariumViewModel by viewModels()
    private var isDataReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnPilgan.setOnClickListener {
            val intent = Intent(this, PilganActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.ivGlosarium.setOnClickListener {
            glosariumViewModel.isLoading.observe(this) { isLoading ->
                showLoading(isLoading)
            }
            glosariumViewModel.glosarium.observe(this) { glosariumList ->
                if (glosariumList.isNotEmpty() && isDataReady) {
                    val intent = Intent(this, GlosariumActivity::class.java)
                    startActivity(intent)
                } else if (!isDataReady) {
                    // Data is not ready, handle accordingly
                    isDataReady = true
                    glosariumViewModel.getGlosarium()
                }
            }
        }
        glosariumViewModel.getGlosarium()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.mainProgressBar.visibility = View.VISIBLE
        } else {
            binding.mainProgressBar.visibility = View.GONE
        }
    }
}
