package com.example.reater.ui.activities

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reater.adapters.QuestionIndexAdapter
import com.example.reater.databinding.ActivityOngoingQuizBinding
import com.example.reater.models.Quiz

class OngoingQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOngoingQuizBinding
    private lateinit var quiz: Quiz
    private val args:OngoingQuizActivityArgs by navArgs()
    private var currentQuestionIndex = 0
    private lateinit var timer: CountDownTimer
    private val qadapter by lazy {QuestionIndexAdapter()}
    private var backButtonPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOngoingQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

         quiz = args.quiz
        //qadapter.questionCount=quiz.quizList.size
        binding.question=quiz.quizList[0]
        setupUi()

    }

    private fun setupUi() {
        binding.qnoRV.adapter=qadapter
        binding.qnoRV.layoutManager=LinearLayoutManager(this)
        binding.progressBar3.max=quiz.quizDuration.toInt()*60000
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



        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(quiz.quizDuration.toLong() * 60000, 1000) { // Convert minutes to milliseconds
            override fun onTick(millisUntilFinished: Long) {
                binding.progressBar3.progress= (millisUntilFinished).toInt()
                val totalSecondsLeft = millisUntilFinished / 1000
                val minutesLeft = totalSecondsLeft / 60
                val secondsLeft = totalSecondsLeft % 60
                binding.editTextTime.text = "$minutesLeft:${String.format("%02d", secondsLeft)}" // Format seconds to always display 2 digits
            }

            override fun onFinish() {
                finish()
            }
        }
        timer.start()
    }
    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you really want to go back?")
        builder.setPositiveButton("Yes") { _, _ ->
            finish() // Finish the activity when "Yes" is clicked
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss() // Dismiss the dialog when "No" is clicked
            startTimer() // Restart the timer
        }
        builder.setCancelable(false) // Prevent dismissing dialog on outside touch or back press
        builder.create().show()
    }

    /*override fun onStop() {
        super.onStop()
        finish()
    }*/

    override fun onBackPressed() {
        // Handle back press event
        if (backButtonPressed) {
            // Display AlertDialog only if back button pressed twice within 2 seconds

            showAlertDialog()

        } else {
            backButtonPressed = true
            timer.start() // Start the timer to reset backButtonPressed after 2 seconds
            // Optionally, you can display a toast or message here indicating that another press is needed
        }
    }


}
