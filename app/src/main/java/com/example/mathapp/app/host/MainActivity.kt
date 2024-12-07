package com.example.mathapp.app.host

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.mathapp.presentation.navigation.DrawerNavigationScreen
import com.example.mathapp.presentation.theme.MathAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetStatusBarColor(Color.White)
            DrawerNavigationScreen()
        }
        //fetchPdfUrl("full_book/algebra_math_app.pdf")
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