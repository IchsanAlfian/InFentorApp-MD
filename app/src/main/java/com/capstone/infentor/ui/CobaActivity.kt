package com.capstone.infentor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.capstone.infentor.R

class CobaActivity : AppCompatActivity() {
    private lateinit var rating1: ImageView
    private lateinit var rating2: ImageView
    private lateinit var rating3: ImageView
    private lateinit var rating4: ImageView
    private lateinit var rating5: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coba)

    }
}
