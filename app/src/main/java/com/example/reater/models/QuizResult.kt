package com.example.reater.models

import com.google.gson.annotations.SerializedName

data class QuizResult(

    @SerializedName("QuizID")
    var id: String,

    @SerializedName("StudentID")
    var studentID: String,

    @SerializedName("ClassID")
    var ClassID: String,

    @SerializedName("SubjectID")
    var SubjectID: String,

    @SerializedName("QuestionsCount")
    var questionsCount: Int,

    @SerializedName("ObtainedMarks")
    var obtainedmarks:Int,

    @SerializedName("MaxMarks")
    var MaxMarks:Int,


)
