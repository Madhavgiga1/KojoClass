package com.example.reater.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.reater.R
import com.example.reater.adapters.ClassAdapter
import com.example.reater.databinding.FragmentHomeBinding
import com.example.reater.utils.NetworkResult
import com.example.reater.viewmodels.MainViewModel

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

        setupRecyclerView()
        requestAPIData()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.classRV.adapter = mAdapter
        binding.classRV.layoutManager= GridLayoutManager(requireContext(),2)
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