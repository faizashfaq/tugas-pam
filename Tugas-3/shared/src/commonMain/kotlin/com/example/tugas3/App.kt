package com.example.tugas3

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ==================================================================
// 1. Reusable Composable #1: ProfileHeader (Avatar Circular & Nama)
// ==================================================================
@Composable
fun ProfileHeader(
    name: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        // Box penumpukan & border lingkaran Foto Profil
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape)
        ) {
            Text(
                text = "👤",
                fontSize = 50.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = role,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

// ==================================================================
// 2. Reusable Composable #2: InfoItem (Baris Kontak dengan Icon)
// ==================================================================
@Composable
fun InfoItem(
    iconEmoji: String,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(
                text = iconEmoji,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// ==================================================================
// 3. Reusable Composable #3: ProfileCard (Card Wrapper Elegan)
// ==================================================================
@Composable
fun ProfileCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

// ==================================================================
// Main App Component
// ==================================================================
@Composable
fun App() {
    var isBioVisible by remember { mutableStateOf(false) }

    MaterialTheme {
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
                // 1. Profile Header
                ProfileHeader(
                    name = "Muhammad Faiz Ashfaq",
                    role = "Teknik Informatika ITERA 2024"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 2. Info Section (ProfileCard & InfoItem)
                ProfileCard(title = "Informasi Kontak") {
                    InfoItem(
                        iconEmoji = "✉️",
                        label = "Email",
                        value = "muhammad.124140144@student.itera.ac.id"
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                    InfoItem(
                        iconEmoji = "📞",
                        label = "Phone",
                        value = "0895-2446-7744"
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                    InfoItem(
                        iconEmoji = "📍",
                        label = "Location",
                        value = "Bandar Lampung"
                    )
                }

                // 3. Tombol Toggle Animasi (Bonus +10%)
                Button(
                    onClick = { isBioVisible = !isBioVisible },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(if (isBioVisible) "Sembunyikan Deskripsi" else "Tampilkan Deskripsi")
                }

                // 4. Animasi Penampakan Bio (AnimatedVisibility)
                AnimatedVisibility(
                    visible = isBioVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    ProfileCard(title = "Deskripsi singkat tentang saya :") {
                        Text(
                            text = "lorem ipsum dolor sit amet consectetur adipiscing elit voluptate do exercitation non pariatur est et distinctio ut nostrud aut temporibus sit aliquip laborum non assumenda non maxime sint facilis temporibus commodo mollit et est consectetur vel eligendi qui consequat dolor harum nostrud occaecat dolor cillum dolores et aut omnis dolore",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }
    }
}