package com.example.tugas_4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugas_4.ui.EditProfileForm
import com.example.tugas_4.ui.InfoItem
import com.example.tugas_4.ui.ProfileCard
import com.example.tugas_4.ui.ProfileHeader
import com.example.tugas_4.viewmodel.ProfileViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip

@Composable
fun App(viewModel: ProfileViewModel = remember { ProfileViewModel() }) {
    val uiState by viewModel.uiState.collectAsState()

    // State lokal temporary untuk penampungan input form edit
    var editName by remember(uiState.name) { mutableStateOf(uiState.name) }
    var editBio by remember(uiState.bio) { mutableStateOf(uiState.bio) }

    // Bonus (+10%): Dynamic Dark/Light Theme Switching
    val colorScheme = if (uiState.isDarkMode) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Dark Mode Toggle Header Switch
                // Dark Mode Toggle Header Switch (Area sentuh sudah diperluas)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (uiState.isDarkMode) "🌙 Dark Mode" else "☀️ Light Mode",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.weight(1f) // Teks tetap statis (tidak bisa diklik)
                    )

                    // Pembungkus khusus untuk memperluas area sentuh Switch
                    Box(
                        modifier = Modifier
                            .clip(CircleShape) // Efek animasi klik (ripple) berbentuk rapi
                            .clickable { viewModel.toggleDarkMode(!uiState.isDarkMode) }
                            .padding(20.dp), // Memperluas area klik sebesar 8dp di sekeliling sakelar
                        contentAlignment = Alignment.Center
                    ) {
                        Switch(
                            checked = uiState.isDarkMode,
                            onCheckedChange = null // Logika klik sepenuhnya ditangani oleh Box pembungkus
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Profile Header
                ProfileHeader(
                    name = uiState.name,
                    role = uiState.role
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Conditionally Render Edit Form or Standard Profile View
                if (uiState.isEditing) {
                    EditProfileForm(
                        editName = editName,
                        editBio = editBio,
                        onNameChange = { editName = it },
                        onBioChange = { editBio = it },
                        onSave = { viewModel.updateProfile(editName, editBio) },
                        onCancel = {
                            editName = uiState.name
                            editBio = uiState.bio
                            viewModel.setEditing(false)
                        }
                    )
                } else {
                    // Section Bio
                    ProfileCard(title = "📌 Bio / Deskripsi") {
                        Text(
                            text = uiState.bio,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 20.sp
                        )
                    }

                    // Section Informasi Kontak
                    ProfileCard(title = "📞 Informasi Kontak") {
                        InfoItem(
                            iconEmoji = "✉️",
                            label = "Email",
                            value = uiState.email
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        InfoItem(
                            iconEmoji = "📱",
                            label = "Phone",
                            value = uiState.phone
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        InfoItem(
                            iconEmoji = "📍",
                            label = "Location",
                            value = uiState.location
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Tombol untuk Membuka Mode Edit
                    Button(
                        onClick = { viewModel.setEditing(true) },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Edit Profil")
                    }
                }
            }
        }
    }
}