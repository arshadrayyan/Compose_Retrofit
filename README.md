## Compose_Retrofit Introduction

The App Describes the response data is loading from the URL with the **GET** Request.

The basic **INTERNET** permission is added to Load the Data from the URL

```bash

https://jsonplaceholder.typicode.com/todos/1

```

Using GET Request

interface JsonPlaceholderService {
    @GET("todos/1")
    suspend fun getTodo(): Todo // Todo is a data class representing your JSON structure
}

## State

To Capture the State from the Interface the response received or not.

LaunchedEffect(true) {
    val todo = fetchData()
    todoState = todo
}

## ScreenShot

![Screen](/Screenshots/Screen.jpeg?raw=true "Launch Screen")