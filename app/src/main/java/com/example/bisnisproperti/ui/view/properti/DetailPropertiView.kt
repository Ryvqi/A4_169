package com.example.bisnisproperti.ui.view.properti

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bisnisproperti.model.Properti

@Composable
fun PropertiDetailContent(
    modifier: Modifier = Modifier,
    properti: Properti
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            DetailRow(judul = "ID Properti", isi = properti.idProperti.toString())
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Nama Properti", isi = properti.namaProperti)
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Deskripsi", isi = properti.deskripsiProperti)
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Lokasi", isi = properti.lokasi)
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Harga", isi = properti.harga)
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Status", isi = properti.statusProperti)
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "ID Jenis", isi = properti.idJenis.toString())
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "ID Pemilik", isi = properti.idPemilik.toString())
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "ID Manajer", isi = properti.idManajer.toString())
        }
    }
}

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