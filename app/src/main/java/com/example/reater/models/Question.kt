package com.example.reater.models

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("qid")
    var qid: String,

    @SerializedName("Questiontext")
    var Questiontext: String,

    @SerializedName("qimage")
    var image: String?=null,

    @SerializedName("qOption1")
    var qOption1: String,

    @SerializedName("qOption2")
    var qOption2: String,


    @SerializedName("qOption3")
    var qOption3: String,

    @SerializedName("qOption2")
    var qOption4: String,

    @SerializedName("CorrectAnswer")
    var CorrectAnswer: String,
)