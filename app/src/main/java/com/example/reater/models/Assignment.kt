package com.example.reater.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Assignment(
    @SerializedName("assignmentid")
    var assignmentid: String,

    @SerializedName("classid")
    var classid: String,

    @SerializedName("subjectID")
    var subjectid: String,

    @SerializedName("DueDate")
    var dueDate:String,

    @SerializedName("Teacher")
    var teacher: String,

    @SerializedName("instructions")
    var instructions:String,

    @SerializedName("AssignmentName")
    var assignmentName: String

    ):Parcelable
