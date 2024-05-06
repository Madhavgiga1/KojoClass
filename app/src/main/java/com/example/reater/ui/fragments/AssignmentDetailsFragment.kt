package com.example.reater.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.reater.databinding.FragmentAssignmentDetailsBinding


class AssignmentDetailsFragment : Fragment() {
    private var _binding:FragmentAssignmentDetailsBinding?=null
    private val binding get() = _binding!!
    private val args: AssignmentDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentAssignmentDetailsBinding.inflate(inflater, container,false)
        binding.assignment=args.assignment
        binding.assignmentSubmitButton.setOnClickListener{
            binding.assignmentUploadBox.visibility=View.VISIBLE

        }

        return binding.root
    }

}