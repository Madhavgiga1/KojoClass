package com.example.reater.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("IDNumber")
    var IDNumber: String,
    @SerializedName("password")
    var password: String,

    )
