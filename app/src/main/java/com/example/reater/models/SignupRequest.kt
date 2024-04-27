package com.example.reater.models

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("EnrollmentID")
    var Enrollment: String,
    @SerializedName("password")
    var password: String,

    @SerializedName("Student")
    var student: Student
)
