package com.example.bisnisproperti.ui.view.jenis

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bisnisproperti.model.JenisProperti
import com.example.bisnisproperti.navigasi.DestinasiNavigasi
import com.example.bisnisproperti.ui.viewmodel.jenis.HomeJenisUiState
import com.example.bisnisproperti.ui.viewmodel.jenis.HomeJenisViewModel
import com.example.bisnisproperti.ui.viewmodel.jenis.PenyediaVMJenis

object DestinasiHomeJenis: DestinasiNavigasi{
    override val route = "home jenis"
    override val titleRes = "Home Jenis"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeJenisView(
    navigateToInsertJenis: () -> Unit,
    onDetailClick: (Int) -> Unit,
    viewModel: HomeJenisViewModel = viewModel(factory = PenyediaVMJenis.Factory)
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Jenis Properti") },
                actions = {
                    IconButton(onClick = { viewModel.getAllJenisProperti() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToInsertJenis) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Jenis Properti")
            }
        }
    ) { innerPadding ->
        JenisPropertiStatus(
            homeJenisUiState = viewModel.jenisPropertiUiState,
            retryAction = { viewModel.getAllJenisProperti() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = { viewModel.deleteJenisProperti(it.idJenis) }
        )
    }
}

@Composable
fun JenisPropertiStatus(
    homeJenisUiState: HomeJenisUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onDeleteClick: (JenisProperti) -> Unit
) {
    when (homeJenisUiState) {
        is HomeJenisUiState.Loading -> LoadingScreen(modifier)
        is HomeJenisUiState.Success ->
            if (homeJenisUiState.jenisProperti.isEmpty()) {
                EmptyScreen(modifier)
            } else {
                JenisPropertiList(
                    jenisPropertiList = homeJenisUiState.jenisProperti,
                    modifier = modifier,
                    onDetailClick = onDetailClick,
                    onDeleteClick = onDeleteClick
                )
            }
        is HomeJenisUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Tidak ada data jenis properti.")
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Gagal memuat data.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = retryAction) {
                Text("Coba Lagi")
            }
        }
    }
}

@Composable
fun JenisPropertiList(
    jenisPropertiList: List<JenisProperti>,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onDeleteClick: (JenisProperti) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(jenisPropertiList) { jenisProperti ->
            JenisPropertiCard(
                jenisProperti = jenisProperti,
                onDetailClick = { onDetailClick(jenisProperti.idJenis) },
                onDeleteClick = { onDeleteClick(jenisProperti) }
            )
        }
    }
}

@Composable
fun JenisPropertiCard(
    jenisProperti: JenisProperti,
    onDetailClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = jenisProperti.namaJenis, style = MaterialTheme.typography.titleLarge)
            Text(text = "Deskripsi: ${jenisProperti.deskripsiJenis}", style = MaterialTheme.typography.bodyMedium)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onDetailClick) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Detail Jenis Properti")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus Jenis Properti")
                }
            }
        }
    }
}