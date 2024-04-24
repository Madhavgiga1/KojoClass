package com.example.reater.data

import com.example.reater.data.network.ReaterApi
import com.example.reater.models.Student
import com.example.reater.models.Subjects
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val KojoAPI: ReaterApi) {

    suspend fun getStudentProfile(queries: Map<String,String>): Response<Student> {
        return KojoAPI.getStudentData(queries)
    }

    suspend fun getStudentClasses(queries: Map<String,String>): Response<Subjects> {
        return KojoAPI.getStudentClassData(queries)
    }
}