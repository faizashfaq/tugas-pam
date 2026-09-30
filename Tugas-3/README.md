# Tugas 3 - My Profile App (Compose Multiplatform)

Aplikasi profil pengguna yang dibangun menggunakan **Compose Multiplatform** untuk memenuhi Tugas Praktikum Pertemuan 3 Mata Kuliah Pengembangan Aplikasi Mobile (ITERA).

---

## 📱 Screenshot Aplikasi
*(Letakkan file gambar hasil screenshot emulator di dalam folder `Tugas-3` dengan nama `screenshot.png`)*

| ![Desktop](screenshot/desktop.png) | ![Android](screenshot/android.png) |

---

## ✨ Fitur & Implementasi
1. **Layout Implementation:** Menggunakan kombinasi `Column` (susun vertikal), `Row` (susun horizontal), dan `Box` (layering/overlay foto profil).
2. **3 Reusable Composables:**
   - `ProfileHeader`: Menampilkan foto profil circular dengan border, nama lengkap, dan status.
   - `InfoItem`: Baris kontak terisolasi (Email, Phone, Location) dengan ikon dan detail informasi.
   - `ProfileCard`: Wrapper `Card` ber-elevasi untuk merapikan setiap *section* UI.
3. **UI Components & Modifiers:** Menggunakan komponen `Text`, `Button`, `Card`, `Surface`, `HorizontalDivider`, serta styling lengkap (`padding`, `size`, `clip`, `background`, `border`).
4. **Bonus Fitur (+10% Animasi):** Menggunakan `AnimatedVisibility` (`fadeIn` dan `fadeOut`) untuk toggle penampakan *section* Bio/Deskripsi.

---

## 🛠️ Cara Menjalankan Proyek
1. Buka folder **`Tugas-3`** melalui Android Studio[cite: 15, 17].
2. Tunggu proses **Gradle Sync** hingga selesai[cite: 17].
3. Pilih konfigurasi run **`androidApp`**[cite: 10].
4. Klik tombol **Play (▶)** untuk menjalankan aplikasi di emulator Android[cite: 10].