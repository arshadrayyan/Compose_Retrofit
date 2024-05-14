package com.jetpackcompose.compose_retrofit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackcompose.compose_retrofit.ui.theme.Compose_RetrofitTheme
import com.jetpackcompose.retrofit_fetch.client.RetrofitClient
import com.jetpackcompose.retrofit_fetch.data.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_RetrofitTheme {
                TodoScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoScreen() {
    var todoState by remember { mutableStateOf<Todo?>(null) }

    LaunchedEffect(true) {
        val todo = fetchData()
        todoState = todo
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            if (todoState != null) {
                DisplayTodo(todoState!!)
            } else {
                LoadingContent()
            }
        }
    )
}

@Composable
fun DisplayTodo(todo: Todo) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            Column {
                Text(
                    text = "User ID: ${todo.userId}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "ID: ${todo.id}",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Title: ${todo.title}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Completed: ${todo.completed}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = if (todo.completed) Color.Green else Color.Red
                )
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

suspend fun fetchData(): Todo {
    return withContext(Dispatchers.IO) {
        RetrofitClient.instance.getTodo()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose_RetrofitTheme {
        TodoScreen()
    }
}