package com.example.quizzapplication

data class QuizzModel(
    val id: String,
    val title: String,
    val description: String,
    val time: String,
    val questionList: List<QuestionModel>
) {
    constructor() : this("", "", "", "", emptyList())
}

data class QuestionModel(
    val question : String,
    val options : List<String>,
    val correct : String,
){
    constructor() : this ("", emptyList(),"")
}

