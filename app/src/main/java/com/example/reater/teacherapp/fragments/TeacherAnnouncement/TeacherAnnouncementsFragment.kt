package com.example.reater.teacherapp.fragments.TeacherAnnouncement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.reater.R
import com.example.reater.databinding.FragmentAddAnnouncementBinding
import com.example.reater.databinding.FragmentAddStudyMaterialBinding
import com.example.reater.databinding.FragmentTeacherAnnouncementsBinding
import com.example.reater.viewmodels.FirebaseViewmodel


class TeacherAnnouncementsFragment : Fragment() {
    private var _binding: FragmentTeacherAnnouncementsBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseViewmodel: FirebaseViewmodel
    var classID:String="IT-E"
    var subjectID:String="IT2122"
    var subjectName:String="PSUC"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseViewmodel = ViewModelProvider(requireActivity())[FirebaseViewmodel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentTeacherAnnouncementsBinding.inflate(layoutInflater,container,false)

        binding.floatingActionButtonannouncement.setOnClickListener {
            var action=TeacherAnnouncementsFragmentDirections.actionTeacherAnnouncementsFragmentToTeacherPostFragment(
                classID,subjectID,subjectName
            )

            findNavController().navigate(action)
        }

        return binding.root
    }



}