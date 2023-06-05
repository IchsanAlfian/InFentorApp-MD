package com.capstone.infentor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.infentor.databinding.ActivityPilganBinding
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
        setupButton()
        repeat(QuestionData.questions.size) {
            userAnswers.add(-1)
        }
        setQuestion(currentQuestionIndex)
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
            userAnswers[currentQuestionIndex] = 1
        }

        btnOption2.setOnClickListener {
            btnOption1.backgroundTintList = normalBackground
            btnOption2.backgroundTintList = clickedBackground
            btnOption3.backgroundTintList = normalBackground
            btnOption4.backgroundTintList = normalBackground
            userAnswers[currentQuestionIndex] = 2
        }

        btnOption3.setOnClickListener {
            btnOption1.backgroundTintList = normalBackground
            btnOption2.backgroundTintList = normalBackground
            btnOption3.backgroundTintList = clickedBackground
            btnOption4.backgroundTintList = normalBackground
            userAnswers[currentQuestionIndex] = 3
        }

        btnOption4.setOnClickListener {
            btnOption1.backgroundTintList = normalBackground
            btnOption2.backgroundTintList = normalBackground
            btnOption3.backgroundTintList = normalBackground
            btnOption4.backgroundTintList = clickedBackground
            userAnswers[currentQuestionIndex] = 4
        }
        binding.btnPrev.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                setQuestion(currentQuestionIndex)
            }
        }

        binding.btnNext.setOnClickListener {
            if (currentQuestionIndex < QuestionData.questions.size - 1) {
                currentQuestionIndex++
                setQuestion(currentQuestionIndex)
            }
        }
        binding.btnSelesai.setOnClickListener {
            showSelectedAnswers()
        }
    }
    private fun setQuestion(index: Int) {
        val question = QuestionData.questions[index]
        binding.tvSoal.text = question.pertanyaan
        binding.btnOption1.apply {
            text = question.opsi1
            backgroundTintList = if (userAnswers[index] == 1) {
                getColorStateList(R.color.secondary)
            } else {
                getColorStateList(R.color.primary)
            }
        }
        binding.btnOption2.apply {
            text = question.opsi2
            backgroundTintList = if (userAnswers[index] == 2) {
                getColorStateList(R.color.secondary)
            } else {
                getColorStateList(R.color.primary)
            }
        }
        binding.btnOption3.apply {
            text = question.opsi3
            backgroundTintList = if (userAnswers[index] == 3) {
                getColorStateList(R.color.secondary)
            } else {
                getColorStateList(R.color.primary)
            }
        }
        binding.btnOption4.apply {
            text = question.opsi4
            backgroundTintList = if (userAnswers[index] == 4) {
                getColorStateList(R.color.secondary)
            } else {
                getColorStateList(R.color.primary)
            }
        }
        binding.tvIndeks.text = "${index + 1} of ${QuestionData.questions.size}"
    }

    private fun showSelectedAnswers() {
        val selectedAnswers = StringBuilder()
        for (i in userAnswers.indices) {
            val question = QuestionData.questions[i]
            val answerIndex = userAnswers[i]
            val selectedAnswer = when (answerIndex) {
                1 -> question.opsi1
                2 -> question.opsi2
                3 -> question.opsi3
                4 -> question.opsi4
                else -> "Belum dipilih"
            }
            selectedAnswers.append("Pertanyaan ${i + 1}: $selectedAnswer\n")
        }
        // Tampilkan jawaban yang dipilih, bisa menggunakan Toast, AlertDialog, atau TextView di layout activity
        // Contoh penggunaan Toast:
        Toast.makeText(this, selectedAnswers.toString(), Toast.LENGTH_SHORT).show()
    }



}