package com.example.bisnisproperti.ui.view.manajer

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
import com.example.bisnisproperti.model.ManajerProperti
import com.example.bisnisproperti.navigasi.DestinasiNavigasi
import com.example.bisnisproperti.ui.viewmodel.manajer.DetailManajerUiState
import com.example.bisnisproperti.ui.viewmodel.manajer.DetailManajerViewModel

object DestinasiDetailManajer: DestinasiNavigasi {
    override val route = "detail manajer"
    override val titleRes = "Detail Manajer"
    const val idManajer = "idmanajer"
    val routeWithArg = "$route/{$idManajer}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailManajerView(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onEditClick: (Int) -> Unit,
    viewModel: DetailManajerViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail Manajer") },
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
                    val state = viewModel.manajerUiState
                    if (state is DetailManajerUiState.Success) {
                        onEditClick(state.manajerProperti.idManajer)
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Manajer")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = viewModel.manajerUiState) {
                is DetailManajerUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is DetailManajerUiState.Error -> {
                    Text(text = "Gagal memuat data manajer.", style = MaterialTheme.typography.bodyMedium)
                }
                is DetailManajerUiState.Success -> {
                    ManajerDetailContent(manajerProperti = state.manajerProperti)
                }
            }
        }
    }
}

@Composable
fun ManajerDetailContent(
    modifier: Modifier = Modifier,
    manajerProperti: ManajerProperti
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
            DetailRow(judul = "ID Manajer", isi = manajerProperti.idManajer.toString())
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Nama Manajer", isi = manajerProperti.namaManajer)
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow(judul = "Kontak Manajer", isi = manajerProperti.kontakManajer)
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