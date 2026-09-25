package com.example.tugas2

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("=========================================")
    println("      NEWS FEED SIMULATOR (KMP)          ")
    println("=========================================\n")

    val repository = NewsRepository()
    val viewModel = NewsViewModel(repository, this)

    // Monitoring StateFlow[cite: 1]
    launch {
        viewModel.readCount.collect { count ->
            println(">>> [STATE UPDATE] Jumlah Berita Dibaca: $count <<<\n")
        }
    }

    println("--- Stream Berita 'Teknologi' (Delay 2 Detik) ---")

    // Collect Flow[cite: 1]
    val newsJob = launch {
        repository.getFilteredNewsStream("Teknologi").collect { newsDisplay ->
            println("\n[UI Display Output]:\n$newsDisplay\n")
        }
    }

    // Simulasi klik detail artikel setelah 3 detik
    launch {
        delay(3000L)
        println("\n>>> [USER ACTION] Pengguna mengklik Artikel ID 1...")
        viewModel.loadArticleDetail(1)
    }

    delay(11000L)

    println("\n>>> Detail Artikel Terakhir Dibuka:")
    println(viewModel.selectedArticle.value?.fullContent ?: "Belum ada detail")

    newsJob.cancel()
    println("\n=========================================")
    println("       SIMULASI SELESAI                   ")
    println("=========================================")
}