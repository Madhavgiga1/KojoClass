package com.example.reater.ui.fragments.SubjectDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reater.R
import com.example.reater.databinding.FragmentAnnouncementsBinding
import com.example.reater.databinding.FragmentFilesBinding


class FilesFragment : Fragment() {

    private var _binding: FragmentFilesBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        _binding= FragmentFilesBinding.inflate(inflater, container,false)
        return binding.root
    }


}