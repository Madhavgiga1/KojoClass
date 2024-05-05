package com.example.reater.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reater.R
import com.example.reater.adapters.ClassAdapter
import com.example.reater.adapters.QuizAdapter
import com.example.reater.databinding.FragmentHomeBinding
import com.example.reater.databinding.FragmentQuizBinding
import com.example.reater.models.Question
import com.example.reater.models.Quiz
import com.example.reater.viewmodels.MainViewModel


class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { QuizAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentQuizBinding.inflate(inflater, container, false)
        setupRecyclerView()

        var questionsList = listOf(
            Question(
                qid = "Q1",
                Questiontext = "What is the square root of 64?",
                qOption1 = "4",
                qOption2 = "6",
                qOption3 = "8",
                qOption4 = "10",
                CorrectAnswer = "Option 3"
            ),
            Question(
                qid = "Q2",
                Questiontext = "What is the capital of France?",
                qOption1 = "London",
                qOption2 = "Berlin",
                qOption3 = "Paris",
                qOption4 = "Rome",
                CorrectAnswer = "Option 3"
            ),
            Question(
                qid = "Q3",
                Questiontext = "What is the chemical symbol for water?",
                qOption1 = "H2O",
                qOption2 = "CO2",
                qOption3 = "NaCl",
                qOption4 = "O2",
                CorrectAnswer = "Option 1"
            ),
            Question(
                qid = "Q4",
                Questiontext = "Who wrote 'Romeo and Juliet'?",
                qOption1 = "William Shakespeare",
                qOption2 = "Charles Dickens",
                qOption3 = "Jane Austen",
                qOption4 = "F. Scott Fitzgerald",
                CorrectAnswer = "Option 1"
            ),
            Question(
                qid = "Q5",
                Questiontext = "What is the largest planet in our solar system?",
                qOption1 = "Earth",
                qOption2 = "Mars",
                qOption3 = "Jupiter",
                qOption4 = "Saturn",
                CorrectAnswer = "Option 3"
            )
        )
        val QUIZ_LIST = listOf(
            Quiz(
                "Monday",
                "Mathematics",
                "60 minutes",
                "MATH101",
                questionsList,
                "Differential Equaitons Quiz",
                "IT-E"
            )
        )
        mAdapter.setData(QUIZ_LIST)
        //mAdapter.setData(QUIZ_LIST)
        return binding.root
    }
    private fun setupRecyclerView() {
        binding.quizRV.adapter = mAdapter
        binding.quizRV.layoutManager= LinearLayoutManager(requireContext())

    }


}