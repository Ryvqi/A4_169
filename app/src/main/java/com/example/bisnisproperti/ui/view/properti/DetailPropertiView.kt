package com.example.bisnisproperti.ui.view.properti

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bisnisproperti.model.Properti
import com.example.bisnisproperti.navigasi.DestinasiNavigasi
import com.example.bisnisproperti.ui.viewmodel.properti.DetailPropertiUiState
import com.example.bisnisproperti.ui.viewmodel.properti.DetailPropertiViewModel

object DestinasiDetailProperti: DestinasiNavigasi {
    override val route = "detail properti"
    override val titleRes = "Detail Properti"
    const val idProperti = "idproperti"
    val routeWithArg = "$route/{$idProperti}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPropertiView(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onEditClick: (Int) -> Unit,
    viewModel: DetailPropertiViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail Properti") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val state = viewModel.propertiUiState
                    if (state is DetailPropertiUiState.Success) {
                        onEditClick(state.properti.idProperti)
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Properti")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = viewModel.propertiUiState) {
                is DetailPropertiUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is DetailPropertiUiState.Error -> {
                    Text(text = "Gagal memuat data properti.", style = MaterialTheme.typography.bodyMedium)
                }
                is DetailPropertiUiState.Success -> {
                    PropertiDetailContent(properti = state.properti)
                }
            }
        }
    }
}

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