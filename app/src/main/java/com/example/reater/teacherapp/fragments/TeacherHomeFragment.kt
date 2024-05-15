package com.example.reater.teacherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reater.R
import com.example.reater.adapters.TeacherHomeClassAdapter
import com.example.reater.databinding.FragmentTeacherHomeBinding
import com.example.reater.models.Coursework
import com.example.reater.utils.NetworkResult
import com.example.reater.viewmodels.FirebaseViewmodel


class TeacherHomeFragment : Fragment() {
    private var _binding:FragmentTeacherHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var firebaseViewmodel: FirebaseViewmodel
    var teacherID="12"
    val mAdapter by lazy {TeacherHomeClassAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseViewmodel=ViewModelProvider(requireActivity())[FirebaseViewmodel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding= FragmentTeacherHomeBinding.inflate(layoutInflater,container,false)

        var classes=fetchClases()
        return binding.root
    }

    private fun fetchClases() {
        binding.TeacherHomeRV.adapter=mAdapter
        binding.TeacherHomeRV.layoutManager=LinearLayoutManager(requireContext())
        firebaseViewmodel.fetchTeacherClasses(teacherID)
        firebaseViewmodel.classlistResponse.observe(viewLifecycleOwner,{result->
            when(result){
                is NetworkResult.Error->{

                }
                is NetworkResult.Success->{

                }
            }
        })
    }
}