package com.example.reater.teacherapp.fragments.TeacherAssignments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reater.R
import com.example.reater.adapters.AssignmentAdapter
import com.example.reater.adapters.TeacherHomeClassAdapter
import com.example.reater.databinding.FragmentTeacherAssignmentBinding
import com.example.reater.databinding.FragmentTeacherClassDetailsBinding
import com.example.reater.utils.NetworkResult
import com.example.reater.viewmodels.FirebaseViewmodel


class TeacherAssignmentFragment : Fragment() {

    private var _binding: FragmentTeacherAssignmentBinding?=null
    private val binding get() = _binding!!
    private lateinit var firebaseViewmodel: FirebaseViewmodel
    var teacherID="12"
    var classid: String="IT-E"
    var subjectID:String="IT2129"
    val mAdapter by lazy { AssignmentAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseViewmodel= ViewModelProvider(requireActivity())[FirebaseViewmodel::class.java]
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding=FragmentTeacherAssignmentBinding.inflate(layoutInflater,container,false)
        binding.teacherRV.adapter=mAdapter
        binding.teacherRV.layoutManager=LinearLayoutManager(requireContext())
        setupScreen()
        return binding.root
    }

    private fun setupScreen() {
        firebaseViewmodel.getAssignmentssRequest(classid, subjectID)
        firebaseViewmodel.getAssignmentsResponse.observe(viewLifecycleOwner,{result->
            when(result){
                is NetworkResult.Error ->{
                    Toast.makeText(requireContext(),"Network Error",Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Success ->{
                    result.data?.let { mAdapter.setData(it) }
                }
            }
        })
    }


}