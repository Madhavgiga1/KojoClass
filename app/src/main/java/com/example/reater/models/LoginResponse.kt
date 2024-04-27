package com.example.reater.models


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var data: Student, // Nested data class
    @SerializedName("id")
    var id: String,
    @SerializedName("message")
    var message: String
) {
    data class Student(
        @SerializedName("EnrollementID")
        val enrollmentID: Int,

        @SerializedName("fullName")
        val fullName: String,

        @SerializedName("DegreeID")
        val degreeID: String,

        @SerializedName("ClassID")
        val classID: String,

        @SerializedName("PhoneNumber")
        val phoneNumber: String,

        @SerializedName("EnrollmentYear")
        val enrollmentYear: Int,

        @SerializedName("Email")
        val email: String,

        @SerializedName("DOB")
        val dob: String,

        @SerializedName("Token")
        val token: String
    )
}
