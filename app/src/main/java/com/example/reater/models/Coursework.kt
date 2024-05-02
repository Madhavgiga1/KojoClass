package com.example.reater.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coursework(
    @SerializedName("SubjectID")
    var SubjectID:String,

    @SerializedName("SubjectName")
    var SubjectName:String,

    @SerializedName("ClassID")
    var ClassID:String,

    @SerializedName("TeacherID")
    var TeacherID:String,

    @SerializedName("TeacherName")
    var TeacherName:String,

    @SerializedName("Credits")
    var Credits:String,

    @SerializedName("Semester")
    var Semester:String,

    @SerializedName("AcademicSession")
    var AcademicSession:String
):Parcelable
