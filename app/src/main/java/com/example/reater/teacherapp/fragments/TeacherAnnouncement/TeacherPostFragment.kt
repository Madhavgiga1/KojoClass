package com.example.reater.teacherapp.fragments.TeacherAnnouncement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.reater.R
import com.example.reater.databinding.FragmentAddAnnouncementBinding
import com.example.reater.databinding.FragmentTeacherPostBinding
import com.example.reater.models.Announcement
import com.example.reater.viewmodels.FirebaseViewmodel
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.*


class TeacherPostFragment : Fragment() {

    private var _binding: FragmentTeacherPostBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseViewmodel: FirebaseViewmodel
    var authordp="SADASD"
    var authorname="Veena Khandelwal"
    var classid:String="IT-E"
    var subjectID:String="IT2121"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseViewmodel= ViewModelProvider(requireActivity())[FirebaseViewmodel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding= FragmentTeacherPostBinding.inflate(layoutInflater,container,false)
        binding.teacherSMSubmitButtonLayout.setOnClickListener {
            addAnnouncement(binding.teacherSMSubmitButtonLayout)
        }
        return binding.root
    }

    private fun addAnnouncement(layout:MaterialCardView) {
        var text=binding.teacherAnnouncementText.text.toString()
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().time)
        var announcement=Announcement(authorname,timestamp,authordp,text)
        firebaseViewmodel.addAnnouncement(layout,classid,subjectID, announcement)

    }

}