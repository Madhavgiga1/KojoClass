package com.example.reater.teacherapp.fragments.TeacherStudyMaterial

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.reater.databinding.FragmentAddStudyMaterialBinding
import com.example.reater.models.StudyMaterial
import com.example.reater.viewmodels.FirebaseViewmodel
import java.text.SimpleDateFormat
import java.util.*

class AddStudyMaterialFragment : Fragment() {

    private var _binding: FragmentAddStudyMaterialBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseViewmodel: FirebaseViewmodel
    private val PICK_FILE_REQUEST_CODE = 1001
    var classID: String = "IT-E"
    var subjectID: String = "IT2125"
    var authorname = "Veena Khandelwal"
    var authordp = "sdhasdas"
    lateinit var fileUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseViewmodel = ViewModelProvider(requireActivity())[FirebaseViewmodel::class.java]
        // TODO: Implement a way to get user's class and author name and stuff
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddStudyMaterialBinding.inflate(inflater, container, false)

        binding.teacherSmAttachBox.setOnClickListener {
            selectFile()
        }

        binding.teacherSmFinalupload.setOnClickListener {
            if (::fileUri.isInitialized) {
                addMaterial()
            } else {
                Toast.makeText(requireContext(), "Please select a file first", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun selectFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "*/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            if (uri != null) {
                fileUri = uri
                Toast.makeText(requireContext(), "File selected: $fileUri", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "File selection failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addMaterial() {
        val filename = binding.teacherUploadStudyMaterialFilename.text.toString()
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().time)
        val fileLocation = UUID.randomUUID().toString()

        val studyMaterial = StudyMaterial(fileLocation, timestamp, authorname, authordp, fileLocation)
        firebaseViewmodel.addMaterial(studyMaterial, classID, subjectID, fileUri, fileLocation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
