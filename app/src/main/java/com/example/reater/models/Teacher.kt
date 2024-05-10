package com.example.reater.models

import com.google.gson.annotations.SerializedName

data class Teacher(

@SerializedName("TeacherID")
val TeacherID: String,

@SerializedName("fullName")
val fullName: String,


@SerializedName("PhoneNumber")
val phoneNumber: String,

@SerializedName("JoiningYear")
val enrollmentYear: String,

@SerializedName("Email")
val email: String,



)
