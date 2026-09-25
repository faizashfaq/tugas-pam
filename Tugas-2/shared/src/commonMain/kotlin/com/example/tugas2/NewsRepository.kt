package com.example.tugas2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class NewsRepository {

    private val dummyArticles = listOf(
        NewsArticle(1, "Inovasi Fitur Baru Kotlin Multiplatform", "Teknologi", "KMP semakin matang untuk cross-platform UI...", "Isi lengkap artikel KMP 2026."),
        NewsArticle(2, "Timnas Indonesia Lolos Kualifikasi", "Olahraga", "Kemenangan dramatis ditentukan di menit akhir...", "Isi lengkap berita sepak bola."),
        NewsArticle(3, "Perkembangan AI dan Machine Learning", "Teknologi", "Model AI terbaru kini lebih efisien dan cepat...", "Isi lengkap artikel AI."),
        NewsArticle(4, "Tips Pola Hidup Sehat untuk Mahasiswa", "Kesehatan", "Konsumsi makanan bergizi dan istirahat cukup...", "Isi lengkap tips kesehatan."),
        NewsArticle(5, "Rilis Laptop M4 MacBook Air", "Teknologi", "Performa kencang dengan konsumsi daya rendah...", "Isi lengkap review MacBook M4.")
    )

    // Flow yang emisi berita setiap 2 detik[cite: 1]
    fun getNewsStream(): Flow<NewsArticle> = flow {
        for (article in dummyArticles) {
            delay(2000L)
            emit(article)
        }
    }

    // Filter kategori, transform text, logging side-effect, & error handling
    fun getFilteredNewsStream(category: String): Flow<String> {
        return getNewsStream()
            .filter { article -> article.category.equals(category, ignoreCase = true) }
            .map { article ->
                "[${article.category.uppercase()}] ${article.title}\n   ${article.contentSnippet}"
            }
            .onEach { formattedText ->
                println("LOG [Stream]: Memproses berita -> \n$formattedText")
            }
            .catch { e ->
                println("LOG ERROR: Terjadi masalah -> ${e.message}")
                emit("Gagal memuat berita terkini.")
            }
    }

    // Coroutine async/await untuk mengambil detail berita secara async[cite: 1, 1]
    suspend fun fetchArticleDetailAsync(articleId: Int): NewsArticle? = coroutineScope {
        val deferredDetail = async(Dispatchers.Default) {
            delay(1000L)
            dummyArticles.find { it.id == articleId }
        }
        deferredDetail.await()
    }
}