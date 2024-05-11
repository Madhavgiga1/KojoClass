package com.example.reater.data.network

import com.example.reater.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface KojoStudentApi {



    @GET("")
    suspend fun getStudentClassData(@QueryMap queries:Map<String, String>):Response<Subjects>

    @POST("")
    suspend fun submitStudentQuiz(@Body quizResult: QuizResult):Response<Unit>

    @GET("")
    suspend fun getQuizzes(queries: Map<String, String>): Response<List<Quiz>>

    @GET("")
    suspend fun getAssignments(queries: Map<String, String>): Response<List<Assignment>>

    @POST("")
    suspend fun turninAssignment(@Body assignmentUpload: AssignmentUpload): Response<Unit>


}