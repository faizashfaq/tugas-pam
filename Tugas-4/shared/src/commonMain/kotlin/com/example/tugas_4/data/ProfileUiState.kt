package com.example.tugas_4.data

data class ProfileUiState(
    val name: String = "Muhammad Faiz Ashfaq",
    val role: String = "Teknik Informatika 2024",
    val email: String = "muhammad.124140144@student.itera.ac.id",
    val phone: String = "0822-3775-1273",
    val location: String = "Bandar Lampung, Indonesia",
    val bio: String = "Mahasiswa Teknik Informatika ITERA yang sedang belajar PAM 2026",
    val isDarkMode: Boolean = false,
    val isEditing: Boolean = false
)