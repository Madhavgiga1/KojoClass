package com.example.reater.data.network

import com.example.reater.models.LoginRequest
import com.example.reater.models.LoginResponse
import com.example.reater.models.SignupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("")
    suspend fun StudentLoginRequest(@Body data: LoginRequest): Response<LoginResponse>

    @POST("")
    suspend fun StudentSignupRequest(@Body data: SignupRequest): Response<LoginResponse>
}