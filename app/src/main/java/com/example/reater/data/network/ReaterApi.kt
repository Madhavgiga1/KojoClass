package com.example.reater.data.network

import com.example.reater.models.Student
import com.example.reater.models.Subjects
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ReaterApi {

    @GET("")
    suspend fun getStudentData(@QueryMap queries:Map<String, String>):Response<Student>

    @GET("")
    suspend fun getStudentClassData(@QueryMap queries:Map<String, String>):Response<Subjects>
}