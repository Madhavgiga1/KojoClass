package com.example.reater.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("EnrollmentID")
    var Enrollment: String,
    @SerializedName("password")
    var password: String
)
