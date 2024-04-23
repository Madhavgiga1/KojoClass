package com.example.reater.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(
    @SerializedName("EnrollementID")
    val enrollmentID: Int,

    @SerializedName("fullName")
    val Full_Name: String,

    @SerializedName("DegreeID")
    val DegreeID: String,

    @SerializedName("ClassID")
    val ClassID: String,

    @SerializedName("PhoneNumber")
    val PhoneNumber: String,

    @SerializedName("EnrollmentYear")
    val EnrollmentYear: Int,

    @SerializedName("DOB")
    val DOB: String

): Parcelable
