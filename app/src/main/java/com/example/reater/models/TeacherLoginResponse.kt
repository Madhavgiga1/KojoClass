package com.example.reater.models

import com.google.gson.annotations.SerializedName

data class TeacherLoginResponse(

    @SerializedName("code")
    var code: Int,


    @SerializedName("teacher")
    var Teacher: Student,

    @SerializedName("Token")
    val token: String,

    @SerializedName("id")
    var id: String,

    @SerializedName("message")
    var message: String
)
