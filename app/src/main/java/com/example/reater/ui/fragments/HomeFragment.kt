package com.example.reater.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reater.R
import com.example.reater.adapters.ClassAdapter
import com.example.reater.databinding.FragmentHomeBinding
import com.example.reater.models.Coursework
import com.example.reater.utils.NetworkResult
import com.example.reater.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { ClassAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.cardcontainer.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_userProfileFragment)
        }
        val courses = listOf(
            Coursework("IT2101", "COMPUTER-NETWORKS", "IT-E", "TID001", "Dr. Sudhir Sharma", "4", "3rd Semester", "JUL-NOV 2021"),
            Coursework("IT2104", "DATA COMMUNICATIONS (PC-4)", "IT-E", "TID0054", "Dr. Pankaj Vyas", " ", "5th Semester", "JUL-NOV 2021"),
            Coursework("IT2130", "Fundamentals of DataScience", "IT-E", "TID0052", "Dr. Veena Khandelwal", "1", "3rd Semester", "JUL-NOV 2022"),
            Coursework("IT2131", "Operating Systems", "IT-E", "TID005", "Dr. Devesh Choudhary", " ", "4th Semester", "JUL-NOV 2022"),
            Coursework("MA2101", "ENGINEERING MATHEMATICS – III", "IT-E", "TID001", "Dr. Sudhir Sharma", " ", "5th Semester", "JUL-NOV 2022")
        )

        setupRecyclerView()
        mAdapter.setData(courses)
        //requestAPIData(courses)

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.classRV.adapter = mAdapter
        binding.classRV.layoutManager= LinearLayoutManager(requireContext())

    }

    private fun requestAPIData(){
        mainViewModel.getStudentClasses(applyQueries())
        mainViewModel.StudentClassResponse.observe(viewLifecycleOwner,{ response->
            when(response){
                is NetworkResult.Loading->{
                }

                is NetworkResult.Error->{
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Success->{
                    response.data?.let {
                        //mAdapter.classlist=c
                    }
                }
            }
        })
    }
    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        return queries
    }


}