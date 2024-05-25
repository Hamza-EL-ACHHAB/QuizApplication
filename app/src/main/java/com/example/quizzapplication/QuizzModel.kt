package com.example.quizzapplication

data class QuizzModel(
    val id: String,
    val title: String,
    val description: String,
    val time: String
) {
    constructor() : this("", "", "", "")
}
