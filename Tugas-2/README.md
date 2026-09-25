# News Feed Simulator - Kotlin Multiplatform

Tugas Praktikum Pertemuan 2 - Advanced Kotlin, Coroutines, dan Flow
Program Studi Teknik Informatika - Institut Teknologi Sumatera (ITERA)

## Fitur Aplikasi
1. **News Stream (Kotlin Flow):** Memancarkan data berita baru secara real-time setiap 2 detik.
2. **Flow Operators:** Menggunakan `.filter()` untuk menyaring kategori (misal: Teknologi), `.map()` untuk memformat teks, `.onEach()` untuk side-effect logging, dan `.catch()` untuk error handling.
3. **State Management (StateFlow):** Melacak dan memperbarui jumlah berita yang telah dibaca secara reactive.
4. **Async Detail Fetching (Coroutines):** Mengambil detail berita secara asynchronous menggunakan `async`/`await` pada `Dispatchers.Default`.
5. **Unit Testing:** Menguji logika Flow dan Coroutine secara terautomasi menggunakan `kotlinx-coroutines-test`.

## Struktur Modul (`shared/commonMain`)
- `NewsArticle.kt`: Model data artikel berita.
- `NewsRepository.kt`: Logika data stream Flow, operator, dan suspend function `async`/`await`.
- `NewsViewModel.kt`: Manajemen state jumlah berita dibaca menggunakan `StateFlow`.
- `App.kt`: Antarmuka Compose Multiplatform yang menampilkan data stream di Android.

## Cara Menjalankan Unit Test

Jalankan perintah berikut di terminal untuk mengeksekusi unit test sekaligus menampilkan log keluaran stream berita (`println`) di terminal:

```bash
./gradlew :shared:jvmTest --rerun-tasks --info