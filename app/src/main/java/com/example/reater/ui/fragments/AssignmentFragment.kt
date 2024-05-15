package com.example.reater.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reater.R
import com.example.reater.adapters.AssignmentAdapter
import com.example.reater.adapters.QuizAdapter
import com.example.reater.databinding.FragmentAssignmentBinding
import com.example.reater.databinding.FragmentQuizBinding
import com.example.reater.models.Assignment
import com.example.reater.viewmodels.MainViewModel


class AssignmentFragment : Fragment() {


    private var _binding: FragmentAssignmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { AssignmentAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAssignmentBinding.inflate(inflater, container, false)
        setupRecyclerView()
        var assignments=listOf(
            Assignment(
                "1",
                "IT-E",
                "IT3126",
                "25/05/2024",
                "Devesh Choudhary",
                "Late submissions will be not be entertained at any cost,so submit by the due date",
                "Process Synocronisation"
            )  ,
            Assignment(
                "202",
                "IT-E",
                "IT2126",
                "25/07/2024",
                "Veena Khandelwal",
                "Late submissions will be not be entertained at any cost,so submit by the due date",
                "Decision Tree Problems"
            )

        )
        mAdapter.setData(assignments as ArrayList<Assignment>)
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.assignmentRV.adapter=mAdapter
        binding.assignmentRV.layoutManager=LinearLayoutManager(requireContext())
    }

}