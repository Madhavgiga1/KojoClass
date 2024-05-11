package com.example.reater.data

import com.example.reater.data.network.KojoStudentApi
import com.example.reater.data.network.UserApi
import com.example.reater.models.*
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val kojoStudentAPI: KojoStudentApi, private val userApi: UserApi) {


    suspend fun getStudentClasses(queries: Map<String,String>): Response<Subjects> {
        return kojoStudentAPI.getStudentClassData(queries)
    }

    suspend fun SigninStudent(loginRequest: LoginRequest):Response<LoginResponse>{
        return userApi.StudentLoginRequest(loginRequest)
    }

    suspend fun RegisterStudent(signupRequest: SignupRequest): Response<LoginResponse>{
        return userApi.StudentSignupRequest(signupRequest)
    }

    suspend fun LoginTeacher(loginRequest: LoginRequest): Response<TeacherLoginResponse>{
        return userApi.TeacherLoginRequest(loginRequest)
    }
}
