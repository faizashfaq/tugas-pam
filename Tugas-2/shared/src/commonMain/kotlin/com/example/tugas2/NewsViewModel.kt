package com.example.tugas2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository,
    private val scope: CoroutineScope
) {

    // 1. MutableStateFlow (private, hanya bisa diubah dari dalam ViewModel)
    private val _readCount = MutableStateFlow(0)

    // 2. Expose sebagai StateFlow read-only menggunakan .asStateFlow()
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    private val _selectedArticle = MutableStateFlow<NewsArticle?>(null)
    val selectedArticle: StateFlow<NewsArticle?> = _selectedArticle.asStateFlow()

    // Increment (Tambah 1)
    fun markAsRead() {
        _readCount.value++
    }

    // Decrement (Kurangi 1, minimal 0)
    fun decrementReadCount() {
        if (_readCount.value > 0) {
            _readCount.value--
        }
    }

    // Reset ke 0
    fun resetReadCount() {
        _readCount.value = 0
    }

    // Coroutine untuk fetch detail artikel
    fun loadArticleDetail(articleId: Int) {
        scope.launch {
            val article = repository.fetchArticleDetailAsync(articleId)
            _selectedArticle.value = article
            if (article != null) {
                markAsRead() // Menambah jumlah dibaca
            }
        }
    }
}