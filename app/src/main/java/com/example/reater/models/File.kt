package com.example.reater.models

import com.google.gson.annotations.SerializedName

data class File(
    @SerializedName("filename")
    var filename: String,


    @SerializedName("timestamp")
    var timestamp: String,

    @SerializedName("Author_Name")
    var authorName: String,

    @SerializedName("Author_DP")
    var authorDP: String
)
