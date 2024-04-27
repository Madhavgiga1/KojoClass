package com.example.reater.models


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var data: StudentResponse, // Nested data class
    @SerializedName("id")
    var id: String,
    @SerializedName("message")
    var message: String
) {
    data class StudentResponse(
        @SerializedName("student")
        var student: Student,

        @SerializedName("Token")
        val token: String
    )
}
