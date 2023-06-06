package com.capstone.infentor

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast
import com.capstone.infentor.databinding.ActivityPilganBinding
import com.capstone.infentor.dummy.Question
import com.capstone.infentor.dummy.QuestionData

class PilganActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPilganBinding
    private var currentQuestionIndex = 0
    private val userAnswers = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilganBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        repeat(QuestionData.questions.size) {
            userAnswers.add(-1)
        }
        binding.rating1.setOnClickListener {
            setRating(1)
        }
        binding.rating2.setOnClickListener {
            setRating(2)
        }
        binding.rating3.setOnClickListener {
            setRating(3)
        }
        binding.rating4.setOnClickListener {
            setRating(4)
        }
        binding.rating5.setOnClickListener {
            setRating(5)
        }
        binding.btnNext.setOnClickListener {
            if (currentQuestionIndex < QuestionData.questions.size - 1) {
                currentQuestionIndex++
                updateQuestion()
            } else {
                Toast.makeText(this, "Kuis selesai", Toast.LENGTH_SHORT).show()
                showUserAnswers()
            }
        }
        binding.btnPrev.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                updateQuestion()
            } else {
                Toast.makeText(this, "Ini pertanyaan pertama", Toast.LENGTH_SHORT).show()
            }
        }
        // panggil pertanyaan pertame
        updateQuestion()
    }
    @SuppressLint("SetTextI18n")
    private fun updateQuestion() {
        val question: Question = QuestionData.questions[currentQuestionIndex]
        binding.tvSoal.text = question.pertanyaan
        binding.tvIndeks.text = "${currentQuestionIndex + 1} of ${QuestionData.questions.size}"
        val userAnswer = userAnswers[currentQuestionIndex]
        setRating(userAnswer)
    }

    private fun setRating(rating: Int) {
        binding.rating1.setImageResource(if (rating == 1) R.drawable.circle_filled else R.drawable.circle_empty)
        binding.rating2.setImageResource(if (rating == 2) R.drawable.circle_filled else R.drawable.circle_empty)
        binding.rating3.setImageResource(if (rating == 3) R.drawable.circle_filled else R.drawable.circle_empty)
        binding.rating4.setImageResource(if (rating == 4) R.drawable.circle_filled else R.drawable.circle_empty)
        binding.rating5.setImageResource(if (rating == 5) R.drawable.circle_filled else R.drawable.circle_empty)
        binding.emote.setImageResource(getEmoteDrawableResource(rating))
        scaleAnimation(binding.emote)
        userAnswers[currentQuestionIndex] = rating
    }
    private fun getEmoteDrawableResource(rating: Int): Int {
        return when (rating) {
            1 -> R.drawable.emote_1
            2 -> R.drawable.emote_2
            3 -> R.drawable.emote_3
            4 -> R.drawable.emote_4
            5 -> R.drawable.emote_5
            else -> R.color.white
        }
    }
//    private fun fadeAnimation(view: View) {
//        val fadeInAnimation = AlphaAnimation(0f, 1f)
//        fadeInAnimation.duration = 1000
//        view.startAnimation(fadeInAnimation)
//    }
    private fun scaleAnimation(view: View) {
        val scaleAnimation = ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 300
        view.startAnimation(scaleAnimation)
    }
    private fun showUserAnswers() {
        val answers = StringBuilder()
        for (i in 0 until userAnswers.size) {
            val question = QuestionData.questions[i]
            val answer = userAnswers[i]
            answers.append("$answer}")
        }
        Toast.makeText(this, answers.toString(), Toast.LENGTH_LONG).show()
    }
}