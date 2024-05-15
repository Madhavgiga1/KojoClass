package com.example.reater.teacherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.reater.R
import com.example.reater.databinding.FragmentAssignmentDetailsBinding
import com.example.reater.databinding.FragmentTeacherClassDetailsBinding


class TeacherClassDetailsFragment : Fragment() {

    val args:TeacherClassDetailsFragmentArgs by navArgs()
    private var _binding: FragmentTeacherClassDetailsBinding?=null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentTeacherClassDetailsBinding.inflate(inflater, container,false)
        var nav=findNavController()
        binding.cardViewA.setOnClickListener {
            nav.navigate(R.id.action_teacherClassDetailsFragment_to_teacherQuizFragment)
        }
        binding.cardViewB.setOnClickListener {
            nav.navigate(R.id.action_teacherClassDetailsFragment_to_studyMaterialsFragment)
        }
        binding.cardViewC.setOnClickListener {
            nav.navigate(R.id.action_teacherClassDetailsFragment_to_teacherAssignmentFragment)
        }
        binding.cardViewD.setOnClickListener {
            nav.navigate(R.id.action_teacherPostFragment_to_teacherAnnouncementsFragment)
        }

        return binding.root
    }



}