package com.example.mathapp.app.host

import android.annotation.SuppressLint
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.Storage
import androidx.lifecycle.lifecycleScope
import com.example.mathapp.presentation.navigation.DrawerNavigationScreen

import com.example.mathapp.presentation.theme.MathAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.FlowType
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

@Serializable
data class Book (
    val id: Int,
    val book_name: String
)

private fun getClient(): SupabaseClient {
    return createSupabaseClient(
        supabaseUrl = "https://runvwdtynqykyljedgem.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJ1bnZ3ZHR5bnF5a3lsamVkZ2VtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzM0MDA4NzAsImV4cCI6MjA0ODk3Njg3MH0.UaB70k4K9pZq98C7p1q_Smhahje96AATusQlwGDKMBE"
    ) {
        install(Postgrest)
        install(io.github.jan.supabase.storage.Storage)
        install(Auth) {
            flowType = FlowType.PKCE
        }
    }
}

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetStatusBarColor(Color.White)
            DrawerNavigationScreen()
        }
        getData()
        //fetchPdfUrl("full_book/algebra_math_app.pdf")
    }

    private fun getData() {
        lifecycleScope.launch {
            val client = getClient()
            val supabaseResponse = client.postgrest["book_pdf_files"].select()
            val data = supabaseResponse.decodeList<Book>()
            Log.d("supabase", data.toString())
        }
    }

//    fun fetchPdfUrl(filePath: String) {
//        lifecycleScope.launch {
//            val pdfUrl = getPdfUrl(filePath)
//            if (pdfUrl != null) {
//                Log.d("PDF_URL", "Fetched URL: $pdfUrl")
//            } else {
//                Log.e("PDF_URL", "Failed to fetch URL")
//            }
//        }
//    }
}

//fun getPdfUrl(filePath: String): String? {
//    val client = getClient()
//    val storage = client.storage
//
//    val bucketName = "pdf-files"
//    return try {
//        val publicUrl = storage.from(bucketName).publicUrl(filePath)
//        publicUrl
//    } catch (e: Exception) {
//        println("Error fetching URL: ${e.message}")
//        null
//    }
//}

@Composable
fun BooksList() {
    var books by remember { mutableStateOf<List<Book>>(listOf()) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            try {
                val client = getClient()
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

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}

@Preview
@Composable
fun Preview() {
    MathAppTheme {
        DrawerNavigationScreen()
    }
}