package com.example.reater.data.network

import com.example.reater.models.LoginRequest
import com.example.reater.models.LoginResponse
import com.example.reater.models.SignupRequest
import com.example.reater.models.Subjects
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface KojoStudentApi {



    @GET("")
    suspend fun getStudentClassData(@QueryMap queries:Map<String, String>):Response<Subjects>



}