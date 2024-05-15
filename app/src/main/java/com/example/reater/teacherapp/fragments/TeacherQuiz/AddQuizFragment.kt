package com.example.reater.teacherapp.fragments.TeacherQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.reater.R
import com.example.reater.databinding.FragmentAddQuizBinding
import com.example.reater.databinding.FragmentTeacherAnnouncementsBinding
import com.example.reater.viewmodels.FirebaseViewmodel


class AddQuizFragment : Fragment() {
    private var _binding:FragmentAddQuizBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseViewmodel: FirebaseViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       firebaseViewmodel=ViewModelProvider(requireActivity())[FirebaseViewmodel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentAddQuizBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}