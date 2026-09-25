package com.example.tugas2

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class NewsRepositoryTest {

    private val repository = NewsRepository()

    @Test
    fun testFilteredNewsStream_returnsOnlySelectedCategory() = runTest {
        // Test operator filter dan transform pada Flow[cite: 1]
        val resultList = repository.getFilteredNewsStream("Teknologi").toList()

        // Memastikan hanya artikel dengan kategori Teknologi yang masuk (3 artikel dummy)
        assertEquals(3, resultList.size)
        resultList.forEach { item ->
            assert(item.contains("[TEKNOLOGI]"))
        }
    }

    @Test
    fun testFetchArticleDetailAsync_returnsCorrectArticle() = runTest {
        // Test coroutine async/await[cite: 1]
        val article = repository.fetchArticleDetailAsync(1)

        assertNotNull(article)
        assertEquals("Inovasi Fitur Baru Kotlin Multiplatform", article.title)
    }
}