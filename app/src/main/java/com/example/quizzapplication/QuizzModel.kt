package com.example.quizzapplication

data class QuizzModel(
    val id: String,
    val title: String,
    val description: String,
    val time: String
    val questionList: List
) {
    constructor() : this("", "", "", "")
}

data class QuestionEntity(
    val question : String,
    val options : List<String>,
    val correct : String,
){
    constructor() : this ("", emptyList(),"")
}

