package com.example.tugas2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    MaterialTheme {
        val repository = remember { NewsRepository() }
        val scope = rememberCoroutineScope()
        val viewModel = remember { NewsViewModel(repository, scope) }

        // Mengambil StateFlow jumlah berita dibaca
        val readCount by viewModel.readCount.collectAsState()

        // List untuk menampung stream berita yang masuk
        val newsList = remember { mutableStateListOf<String>() }

        // Collect Flow berita di UI Android
        LaunchedEffect(Unit) {
            repository.getFilteredNewsStream("Teknologi").collect { formattedNews ->
                newsList.add(formattedNews)
            }
        }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = "News Feed Simulator (KMP)",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Jumlah Berita Dibaca: $readCount",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Stream Berita Teknologi (Setiap 2 Detik):",
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Menampilkan stream berita
            newsList.forEach { news ->
                Text(
                    text = news,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}