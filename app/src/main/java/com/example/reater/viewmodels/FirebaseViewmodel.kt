package com.example.reater.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reater.models.Announcement
import com.example.reater.models.File
import com.example.reater.utils.NetworkResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import javax.inject.Inject

class FirebaseViewmodel:ViewModel() {
    var database= FirebaseDatabase.getInstance().reference

    var AnnouncementsResponse:MutableLiveData<NetworkResult<List<Announcement>>> = MutableLiveData()
    var FilesResponse:MutableLiveData<NetworkResult<List<File>>> = MutableLiveData()


    fun SendAnnouncementsRequest(classid: String) {
        var post=database.child(classid).child("Announcements")

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

    fun getStudyMaterials(classid:String){
        var materiallocation=database.child(classid).child("Materials")

        materiallocation.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var materials=snapshot.children.mapNotNull{it.getValue(File::class.java)}
                FilesResponse.value=NetworkResult.Success(materials)
            }

            override fun onCancelled(error: DatabaseError) {
                AnnouncementsResponse.value=NetworkResult.Error("We encountered an error")
            }
        })
    }
}