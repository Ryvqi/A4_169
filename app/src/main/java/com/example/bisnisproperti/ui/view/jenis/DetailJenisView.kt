package com.example.bisnisproperti.ui.view.jenis

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
import com.example.bisnisproperti.model.JenisProperti
import com.example.bisnisproperti.navigasi.DestinasiNavigasi
import com.example.bisnisproperti.ui.viewmodel.jenis.DetailJenisUiState
import com.example.bisnisproperti.ui.viewmodel.jenis.DetailJenisViewModel

object DestinasiDetailJenis: DestinasiNavigasi{
    override val route = "detail jenis"
    override val titleRes = "Detail Jenis"
    const val idJenis = "idjenis"
    val routeWithArg = "$route/{$idJenis}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailJenisView(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onEditClick: (Int) -> Unit,
    viewModel: DetailJenisViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail Jenis Properti") },
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
                    val state = viewModel.jenisPropertiUiState
                    if (state is DetailJenisUiState.Success) {
                        onEditClick(state.jenisProperti.idJenis)
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Jenis Properti")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = viewModel.jenisPropertiUiState) {
                is DetailJenisUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is DetailJenisUiState.Error -> {
                    Text(text = "Gagal memuat data jenis properti.", style = MaterialTheme.typography.bodyMedium)
                }
                is DetailJenisUiState.Success -> {
                    JenisPropertiDetailContent(jenisProperti = state.jenisProperti)
                }
            }
        }
    }
}

@Composable
fun JenisPropertiDetailContent(
    modifier: Modifier = Modifier,
    jenisProperti: JenisProperti
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
            DetailRow(judul = "ID Jenis", isi = jenisProperti.idJenis.toString())
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Nama Jenis", isi = jenisProperti.namaJenis)
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Deskripsi", isi = jenisProperti.deskripsiJenis)
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