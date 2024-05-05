package com.example.reater.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.example.reater.databinding.ActivityOngoingQuizBinding
import com.example.reater.models.Quiz

class OngoingQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOngoingQuizBinding
    private lateinit var quiz: Quiz
    private val args:OngoingQuizActivityArgs by navArgs()
    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOngoingQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var quiz = args.quiz

       binding.previousQuestion.setOnClickListener {
            if (currentQuestionIndex == 0) {
                Toast.makeText(this, "This is the first question only", Toast.LENGTH_SHORT).show()
            } else {
                currentQuestionIndex--
                binding.question = quiz.quizList[currentQuestionIndex]
            }
        }

        binding.nextQuestion.setOnClickListener {
            if (currentQuestionIndex == quiz.quizList.size - 1) {
                Toast.makeText(this, "This is the last question", Toast.LENGTH_SHORT).show()
            } else {
                currentQuestionIndex++
                binding.question = quiz.quizList[currentQuestionIndex]
            }
        }
    }

    /*override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }*/
}
