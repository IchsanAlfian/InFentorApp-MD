package com.capstone.infentor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.infentor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menambahkan event listener untuk MaterialButton
        binding.pr.setOnClickListener {
            val intent = Intent(this, PilganActivity::class.java)
            startActivity(intent)
        }
    }
}
