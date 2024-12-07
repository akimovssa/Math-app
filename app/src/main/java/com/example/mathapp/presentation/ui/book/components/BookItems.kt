package com.example.mathapp.presentation.ui.book.components

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mathapp.app.host.SupabaseClientConn
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable

@Serializable
data class Book (
    val id: Int,
    val book_name: String
)

@Composable
fun BooksList() {
    var books by remember { mutableStateOf<List<Book>>(listOf()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val client = SupabaseClientConn.getClient()
                books = client.postgrest.from("book_pdf_files")
                    .select()
                    .decodeList<Book>()
            } catch (e: Exception) {
                Log.e("BooksList error", "Error fetching books: ${e.message}")
            }
        }
    }
    LazyColumn {
        items(
            books, key = { it.id }
        ) { book ->
            Text(
                book.book_name,
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}