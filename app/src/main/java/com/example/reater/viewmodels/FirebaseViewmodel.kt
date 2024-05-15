package com.example.reater.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.reater.R
import com.example.reater.models.Announcement
import com.example.reater.models.Assignment
import com.example.reater.models.Coursework
import com.example.reater.models.StudyMaterial
import com.example.reater.utils.NetworkResult
import com.google.android.material.card.MaterialCardView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FirebaseViewmodel @Inject constructor(application: Application):AndroidViewModel(application) {
    var database= FirebaseDatabase.getInstance().reference


    var AnnouncementsResponse:MutableLiveData<NetworkResult<List<Announcement>>> = MutableLiveData()
    var filesResponse:MutableLiveData<NetworkResult<List<StudyMaterial>>> = MutableLiveData()

    var storage=FirebaseStorage.getInstance()
    fun getAnnouncementsRequest(classid: String, subjectID:String) {
        var post=database.child("Announcements").child(classid).child(subjectID)

        post.addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var announcements= mutableListOf<Announcement>()
                for(postSnapshot in snapshot.children){
                    val announcement=postSnapshot.getValue(Announcement::class.java)
                    announcements.add(announcement!!)
                }
                Log.d("FirebaseViewModel", "Announcements: $announcements")
                AnnouncementsResponse.value=NetworkResult.Success(announcements)
            }

            override fun onCancelled(error: DatabaseError) {
                AnnouncementsResponse.value=NetworkResult.Error("We encountered an error")
            }
        })
    }

    fun getStudyMaterials(classid:String,subjectID: String){
        var materiallocation=database.child("StudyMaterial").child(classid).child(subjectID)

        materiallocation.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var materials=mutableListOf<StudyMaterial>()
                for(postSnapshot in snapshot.children){
                    val material=postSnapshot.getValue(StudyMaterial::class.java)
                    materials.add(material!!)
                }
                filesResponse.value=NetworkResult.Success(materials)
            }

            override fun onCancelled(error: DatabaseError) {
                AnnouncementsResponse.value=NetworkResult.Error("We encountered an error")
            }
        })
    }

    fun addMaterial(studyMaterial: StudyMaterial,classid: String,subjectID: String,fileUri: Uri,locationID: String){


        if(hasInternetConnection()){

            viewModelScope.launch {
                try {
                    val storageRef = storage.reference.child("StudyMaterial/$classid/$subjectID/$locationID")

                    storageRef.putFile(fileUri)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                var addsm=database.child("StudyMaterial/$classid/$subjectID")
                                addsm.setValue(studyMaterial).
                                        addOnSuccessListener {
                                            Log.d("UserRepository", "User added successfully")
                                        }

                            } else {

                                Log.e("UploadViewModel", "File upload failed: ${task.exception?.message}")
                            }
                        }
                } catch (e: Exception) {

                    Log.e("UploadViewModel", "File upload failed: ${e.message}", e)
                }
            }


        }
        else {
            Toast.makeText(getApplication(), "Please Try again", Toast.LENGTH_SHORT).show()
        }
    }
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
    private fun uploadToFirebaseStorage(classid: String, subjectID: String, fileUri: Uri, locationID: String) {

        viewModelScope.launch {
            try {
                val storageRef: StorageReference = storage.reference.child("StudyMaterial").child("$classid/$subjectID/$locationID")

                val uploadTask = storageRef.putFile(fileUri)

                uploadTask.await()
                val downloadUrl = storageRef.downloadUrl.await()

                Log.d("UploadViewModel", "File uploaded successfully. Download URL: $downloadUrl")

            }
            catch (e: Exception) {
                Log.e("UploadViewModel", "File upload failed: ${e.message}", e)
            }
        }
    }

    fun addAnnouncement(layout:MaterialCardView,classid: String,subjectID: String,announcement: Announcement){


        if(hasInternetConnection()){

            var announcement=database.child("Announcements").child(classid).child(subjectID)
            announcement.setValue(announcement)
                .addOnSuccessListener {
                    Toast.makeText(getApplication(),"Announcement Successfully added",Toast.LENGTH_LONG).show()
                    layout.findNavController().navigate(R.id.action_teacherPostFragment_to_teacherAnnouncementsFragment)

                }
                .addOnFailureListener{
                    Toast.makeText(getApplication(),"Announcement not added",Toast.LENGTH_LONG).show()
                }



        }
        else {
            Toast.makeText(getApplication(), "Please Try again", Toast.LENGTH_SHORT).show()
        }
    }

    var classlistResponse:MutableLiveData<NetworkResult<List<Coursework>>> = MutableLiveData()

    fun fetchTeacherClasses(teacherID: String) {

        val databaseReference = database.child("Coursework").child(teacherID)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val classList = ArrayList<Coursework>()
                for (postSnapshot in snapshot.children) {
                    val coursework = postSnapshot.getValue(Coursework::class.java)
                    coursework?.let {
                        classList.add(it)
                    }
                }
                classlistResponse.value = NetworkResult.Success(classList)  // Notify observers of the successful result
            }

            override fun onCancelled(error: DatabaseError) {
                classlistResponse.value = NetworkResult.Error("We encountered an error: ${error.message}")
            }
        })
    }
    var getAssignmentsResponse:MutableLiveData<NetworkResult<ArrayList<Assignment>>> = MutableLiveData()
    fun getAssignmentssRequest(classid: String, subjectID:String) {
        var assignmentsref=database.child("Assignments").child(classid).child(subjectID)
        var assignments= ArrayList<Assignment>()
        assignmentsref.addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for(postSnapshot in snapshot.children){
                    val currentassignment=postSnapshot.getValue(Assignment::class.java)
                    assignments.add(currentassignment!!)
                }

                getAssignmentsResponse.value=NetworkResult.Success(assignments)
            }

            override fun onCancelled(error: DatabaseError) {
                getAssignmentsResponse.value=NetworkResult.Error("We encountered an error")
            }
        })
    }
}