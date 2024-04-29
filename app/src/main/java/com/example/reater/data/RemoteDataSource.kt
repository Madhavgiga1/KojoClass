package com.example.reater.data

import com.example.reater.data.network.KojoApi
import com.example.reater.data.network.UserApi
import com.example.reater.models.LoginRequest
import com.example.reater.models.LoginResponse
import com.example.reater.models.SignupRequest
import com.example.reater.models.Subjects
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val kojoAPI: KojoApi,private val userApi: UserApi) {


    suspend fun getStudentClasses(queries: Map<String,String>): Response<Subjects> {
        return kojoAPI.getStudentClassData(queries)
    }

    suspend fun SigninStudent(loginRequest: LoginRequest):Response<LoginResponse>{
        return userApi.StudentLoginRequest(loginRequest)
    }

    suspend fun RegisterStudent(signupRequest: SignupRequest): Response<LoginResponse>{
        return userApi.StudentSignupRequest(signupRequest)
    }
}
