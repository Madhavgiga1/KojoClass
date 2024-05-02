package com.example.reater.ui.fragments.SubjectDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reater.R
import com.example.reater.adapters.AnnouncementsAdapter
import com.example.reater.databinding.FragmentAnnouncementsBinding
import com.example.reater.models.Announcement
import com.example.reater.models.Coursework
import com.example.reater.utils.Constants
import com.example.reater.utils.NetworkResult
import com.example.reater.viewmodels.FirebaseViewmodel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AnnouncementFragment : Fragment() {

    private var _binding:FragmentAnnouncementsBinding?=null
    private val binding get() = _binding!!
    private lateinit var firebaseViewmodel: FirebaseViewmodel
    private val announcementAdapter by lazy{AnnouncementsAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseViewmodel=ViewModelProvider(requireActivity())[FirebaseViewmodel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentAnnouncementsBinding.inflate(inflater, container, false)


        var args=arguments
        setupRecyclerView()
        var coursework=args?.getParcelable<Coursework>(Constants.COURSEWORK_RESULT_KEY) as Coursework
        requestData(coursework.ClassID)
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.announcementRv.adapter=announcementAdapter
        binding.announcementRv.layoutManager=LinearLayoutManager(requireContext())

    }

    private fun requestData(classID: String) {
        firebaseViewmodel.SendAnnouncementsRequest(classID)
        firebaseViewmodel.AnnouncementsResponse.observe(viewLifecycleOwner,{response->
            when(response){
                is NetworkResult.Success->{
                    response.data.let{
                        if (it != null) {
                            announcementAdapter.setData(it)
                        }
                        else{
                            Toast.makeText(
                                requireContext(),
                                "Null data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                is NetworkResult.Error->{
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }


}