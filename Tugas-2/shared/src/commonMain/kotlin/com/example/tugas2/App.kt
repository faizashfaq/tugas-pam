package com.example.tugas2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
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

        // Menerima perubahan StateFlow secara reactive
        val readCount by viewModel.readCount.collectAsState()

        // List menampung stream berita
        val newsList = remember { mutableStateListOf<String>() }

        // Collect Flow berita & otomatis tambah counter saat berita masuk
        LaunchedEffect(Unit) {
            repository.getFilteredNewsStream("Teknologi").collect { formattedNews ->
                newsList.add(formattedNews)

                // TAMBAHKAN BARIS INI: Panggil ViewModel untuk menambah counter dibaca
                viewModel.markAsRead()
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

            // Tampilkan list berita
            newsList.forEach { news ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = news,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}