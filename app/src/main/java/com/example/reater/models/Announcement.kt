package com.example.reater.models

import com.google.gson.annotations.SerializedName

data class Announcement(
    @SerializedName("Author")
    var author: String = "",

    @SerializedName("TimeStamp")
    var timeStamp: String = "",

    @SerializedName("AuthorPic")
    var authorpicture: String = "",

    @SerializedName("Description")
    var description: String = ""
) {
    // No-argument constructor required by Firebase
    constructor() : this("", "", "", "")
}
