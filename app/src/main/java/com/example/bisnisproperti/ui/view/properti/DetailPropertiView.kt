package com.example.bisnisproperti.ui.view.properti

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailRow(
    modifier: Modifier = Modifier,
    judul: String,
    isi: String
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "$judul:",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = isi,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}