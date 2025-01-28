package com.example.bisnisproperti.ui.view.pemilik

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
import com.example.bisnisproperti.model.Pemilik
import com.example.bisnisproperti.navigasi.DestinasiNavigasi
import com.example.bisnisproperti.ui.viewmodel.pemilik.DetailPemilikUiState
import com.example.bisnisproperti.ui.viewmodel.pemilik.DetailPemilikViewModel

object DestinasiDetailPemilik: DestinasiNavigasi {
    override val route = "detail pemilik"
    override val titleRes = "Detail Pemilik"
    const val idPemilik = "idpemilik"
    val routeWithArg = "$route/{$idPemilik}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPemilikView(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onEditClick: (Int) -> Unit,
    viewModel: DetailPemilikViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail Pemilik") },
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
                    val state = viewModel.pemilikUiState
                    if (state is DetailPemilikUiState.Success) {
                        onEditClick(state.pemilik.idPemilik)
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Pemilik")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = viewModel.pemilikUiState) {
                is DetailPemilikUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is DetailPemilikUiState.Error -> {
                    Text(text = "Gagal memuat data pemilik.", style = MaterialTheme.typography.bodyMedium)
                }
                is DetailPemilikUiState.Success -> {
                    PemilikDetailContent(pemilik = state.pemilik)
                }
            }
        }
    }
}

@Composable
fun PemilikDetailContent(
    modifier: Modifier = Modifier,
    pemilik: Pemilik
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
            DetailRow(judul = "ID Pemilik", isi = pemilik.idPemilik.toString())
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Nama Pemilik", isi = pemilik.namaPemilik)
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Kontak Pemilik", isi = pemilik.kontakPemilik)
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