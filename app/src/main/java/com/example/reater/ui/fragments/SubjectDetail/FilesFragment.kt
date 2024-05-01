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
import com.example.reater.models.StudyMaterial
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
        /*val database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val studymaterials = listOf(
            StudyMaterial(
                "Process Scheduling",
                "2024-04-27 10:00 AM",
                "Veena Khandelwal",
                "https://firebasestorage.googleapis.com/v0/b/kojoclass-c293e.appspot.com/o/veena%20mam.jpeg?alt=media&token=481b455d-2512-4822-8fd8-a1406010baa2\"",
                "https://firebasestorage.googleapis.com/v0/b/kojoclass-c293e.appspot.com/o/Understanding_IAM_For_Cloud_Full.pdf?alt=media&token=93446e82-2d32-4238-a94b-be7736dec029"
            ),
            StudyMaterial(
                "Process Synchronisation",
                "2024-04-27 10:00 AM",
                "Veena Khandelwal",
                "https://firebasestorage.googleapis.com/v0/b/kojoclass-c293e.appspot.com/o/veena%20mam.jpeg?alt=media&token=481b455d-2512-4822-8fd8-a1406010baa2\"",
                "https://firebasestorage.googleapis.com/v0/b/kojoclass-c293e.appspot.com/o/Understanding_IAM_For_Cloud_Full.pdf?alt=media&token=93446e82-2d32-4238-a94b-be7736dec029"
            ),
            StudyMaterial(
                "Page Replacement Algo",
                "2024-04-27 10:00 AM",
                "Veena Khandelwal",
                "https://firebasestorage.googleapis.com/v0/b/kojoclass-c293e.appspot.com/o/veena%20mam.jpeg?alt=media&token=481b455d-2512-4822-8fd8-a1406010baa2\"",
                "https://firebasestorage.googleapis.com/v0/b/kojoclass-c293e.appspot.com/o/Understanding_IAM_For_Cloud_Full.pdf?alt=media&token=93446e82-2d32-4238-a94b-be7736dec029"
            )
        )
        database.child("IT-E").child("StudyMaterial").setValue(studymaterials)*/
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.fileRV.adapter=filesAdapter
        binding.fileRV.layoutManager= LinearLayoutManager(requireContext())
        requestData("IT-E")
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