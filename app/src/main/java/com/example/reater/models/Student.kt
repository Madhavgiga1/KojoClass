package com.example.reater.models

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("EnrollementID")
    val enrollmentID: String,

    @SerializedName("fullName")
    val fullName: String,

    @SerializedName("DegreeID")
    val degreeID: String,

    @SerializedName("ClassID")
    val classID: String,

    @SerializedName("PhoneNumber")
    val phoneNumber: String,

    @SerializedName("EnrollmentYear")
    val enrollmentYear: String,

    @SerializedName("Email")
    val email: String,

    @SerializedName("DOB")
    val dob: String
)
