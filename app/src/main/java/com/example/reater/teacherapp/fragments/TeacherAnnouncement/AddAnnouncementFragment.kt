package com.example.reater.teacherapp.fragments.TeacherAnnouncement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.reater.R
import com.example.reater.databinding.FragmentAddAnnouncementBinding
import com.example.reater.databinding.FragmentAddStudyMaterialBinding
import com.example.reater.viewmodels.FirebaseViewmodel

class AddAnnouncementFragment : Fragment() {
    private var _binding: FragmentAddAnnouncementBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseViewmodel: FirebaseViewmodel
    var authordp="SADASD"
    var authorname="Veena Khandelwal"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       firebaseViewmodel= ViewModelProvider(requireActivity())[FirebaseViewmodel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding= FragmentAddAnnouncementBinding.inflate(layoutInflater,container,false)
        addAnnouncement()
        return binding.root
    }

    private fun addAnnouncement() {
        var text=binding.teacherAnnouncementText.text

    }


}