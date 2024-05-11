package com.example.reater.models


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    var code: Int,

    @SerializedName("student")
    var student: Student,

    @SerializedName("Token")
    val token: String,


    @SerializedName("id")
    var id: String,

    @SerializedName("message")
    var message: String
)
