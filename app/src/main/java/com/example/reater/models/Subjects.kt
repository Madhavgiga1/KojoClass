package com.example.reater.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subjects(
    @SerializedName("SubjectID")
    val SubjectID: String,

    @SerializedName("SubjectName")
    val SubjectName: String,

    @SerializedName("SubjectDescription")
    val SubjectDescription: String,

    @SerializedName("LECTURE_ID")
    val LECTURE_ID: String,

    @SerializedName("SubjectID")
    val name: String

): Parcelable
