package com.example.reater.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Quiz(
    @SerializedName("quizSchedule")
    var quizSchedule: String,

    @SerializedName("subject_id")
    var subjectId: String,

    @SerializedName("quiz_list")
    var quizList:@RawValue List<Question>,

    @SerializedName("QuizName")
    var quizname: String
):Parcelable
