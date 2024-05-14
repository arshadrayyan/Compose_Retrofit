package com.jetpackcompose.retrofit_fetch.data

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)