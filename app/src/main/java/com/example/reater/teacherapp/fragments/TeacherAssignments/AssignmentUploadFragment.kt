package com.example.reater.teacherapp.fragments.TeacherAssignments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reater.databinding.FragmentAssignmentCreateBinding
import com.example.reater.databinding.FragmentAssignmentDetailsBinding


class AssignmentUploadFragment : Fragment() {
    private var _binding: FragmentAssignmentCreateBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       _binding= FragmentAssignmentCreateBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}