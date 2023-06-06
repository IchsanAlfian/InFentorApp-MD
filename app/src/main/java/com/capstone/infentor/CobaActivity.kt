package com.capstone.infentor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast

class CobaActivity : AppCompatActivity() {
    private lateinit var rating1: ImageView
    private lateinit var rating2: ImageView
    private lateinit var rating3: ImageView
    private lateinit var rating4: ImageView
    private lateinit var rating5: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coba)

        rating1 = findViewById(R.id.rating1)
        rating2 = findViewById(R.id.rating2)
        rating3 = findViewById(R.id.rating3)
        rating4 = findViewById(R.id.rating4)
        rating5 = findViewById(R.id.rating5)

        rating1.setOnClickListener {
            setRating(1)
        }

        rating2.setOnClickListener {
            setRating(2)
        }

        rating3.setOnClickListener {
            setRating(3)
        }

        rating4.setOnClickListener {
            setRating(4)
        }

        rating5.setOnClickListener {
            setRating(5)
        }
    }

    private fun setRating(rating: Int) {
        rating1.setImageResource(if (rating >= 1) R.drawable.circle_filled else R.drawable.circle_empty)
        rating2.setImageResource(if (rating >= 2) R.drawable.circle_filled else R.drawable.circle_empty)
        rating3.setImageResource(if (rating >= 3) R.drawable.circle_filled else R.drawable.circle_empty)
        rating4.setImageResource(if (rating >= 4) R.drawable.circle_filled else R.drawable.circle_empty)
        rating5.setImageResource(if (rating >= 5) R.drawable.circle_filled else R.drawable.circle_empty)
    }
}
