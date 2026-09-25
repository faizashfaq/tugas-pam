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

    // StateFlow jumlah berita dibaca[cite: 1]
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    private val _selectedArticle = MutableStateFlow<NewsArticle?>(null)
    val selectedArticle: StateFlow<NewsArticle?> = _selectedArticle.asStateFlow()

    fun markAsRead() {
        _readCount.value += 1
    }

    fun loadArticleDetail(articleId: Int) {
        scope.launch {
            val article = repository.fetchArticleDetailAsync(articleId)
            _selectedArticle.value = article
            if (article != null) {
                markAsRead()
            }
        }
    }
}