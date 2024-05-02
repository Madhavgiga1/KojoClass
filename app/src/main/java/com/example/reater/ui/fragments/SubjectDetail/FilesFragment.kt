package com.example.reater.ui.fragments.SubjectDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reater.adapters.FilesAdapter
import com.example.reater.databinding.FragmentFilesBinding
import com.example.reater.models.Coursework
import com.example.reater.models.StudyMaterial
import com.example.reater.utils.Constants.Companion.COURSEWORK_RESULT_KEY
import com.example.reater.utils.NetworkResult
import com.example.reater.viewmodels.FirebaseViewmodel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class FilesFragment : Fragment() {

    private var _binding: FragmentFilesBinding?=null
    private val binding get() = _binding!!
    private lateinit var firebaseViewmodel: FirebaseViewmodel
    private val filesAdapter by lazy{ FilesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseViewmodel= ViewModelProvider(requireActivity())[FirebaseViewmodel::class.java]
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        _binding= FragmentFilesBinding.inflate(inflater, container,false)
        var args=arguments
        setupRecyclerView()
        var coursework=args?.getParcelable<Coursework>(COURSEWORK_RESULT_KEY) as Coursework
        requestData(coursework.ClassID)
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.fileRV.adapter=filesAdapter
        binding.fileRV.layoutManager= LinearLayoutManager(requireContext())

    }

    private fun requestData(classID: String) {
        firebaseViewmodel.getStudyMaterials(classID)
        firebaseViewmodel.filesResponse.observe(viewLifecycleOwner,{ response->
            when(response){
                is NetworkResult.Success->{
                    response.data.let{
                        if (it != null) {
                            filesAdapter.setData(it)
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