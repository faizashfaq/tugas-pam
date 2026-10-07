package com.example.tugas_4

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform