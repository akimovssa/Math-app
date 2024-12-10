package com.example.mathapp.presentation.ui.book

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.VerticalPdfReaderState

class BouquetViewModel : ViewModel() {
    val pdfUri: Uri = Uri.parse("https://runvwdtynqykyljedgem.supabase.co/storage/v1/object/sign/pdf-files/full_book/algebra_math_app.pdf?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJwZGYtZmlsZXMvZnVsbF9ib29rL2FsZ2VicmFfbWF0aF9hcHAucGRmIiwiaWF0IjoxNzMzNzY0MzY3LCJleHAiOjE3NjUzMDAzNjd9.GufYWPLCa5kyFdI0d9on2-zb-k20Dcaw5GNlj4oJ8pA&t=2024-12-09T17%3A12%3A46.153Z")
}

@Composable
fun PdfContent() {
    val viewModel: BouquetViewModel = viewModel()
    val context = LocalContext.current
    val pdfState = remember {
        VerticalPdfReaderState(
            resource = ResourceType.Remote(viewModel.pdfUri.toString()),
            isZoomEnable = true
        )
    }

    LaunchedEffect(key1 = pdfState.error) {
        pdfState.error?.let {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        } ?: pdfState.isLoaded.let {
            Toast.makeText(context, "Загрузка...", Toast.LENGTH_LONG).show()
        }
    }

    VerticalPDFReader(
        state = pdfState,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun BookScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PdfContent()
        }
    }


}