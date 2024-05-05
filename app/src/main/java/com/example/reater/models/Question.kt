package com.example.reater.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(
    @SerializedName("qid")
    var qid: String,

    @SerializedName("Questiontext")
    var Questiontext: String,

    @SerializedName("qimage")
    var image: String? = null,

    @SerializedName("qOption1")
    var qOption1: String,

    @SerializedName("qOption2")
    var qOption2: String,

    @SerializedName("qOption3")
    var qOption3: String,

    @SerializedName("qOption4")
    var qOption4: String,

    @SerializedName("CorrectAnswer")
    var CorrectAnswer: String
):Parcelable
