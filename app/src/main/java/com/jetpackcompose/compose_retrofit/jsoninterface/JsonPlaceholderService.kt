package com.jetpackcompose.retrofit_fetch.jsoninterface

import com.jetpackcompose.retrofit_fetch.data.Todo
import retrofit2.http.GET

interface JsonPlaceholderService {
    @GET("todos/1")
    suspend fun getTodo(): Todo // Todo is a data class representing your JSON structure
}