package com.example.bisnisproperti.ui.view.properti

import androidx.compose.foundation.clickable
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
import com.example.bisnisproperti.model.Properti
import com.example.bisnisproperti.navigasi.DestinasiNavigasi
import com.example.bisnisproperti.ui.viewmodel.properti.HomePropertiUiState
import com.example.bisnisproperti.ui.viewmodel.properti.HomePropertiViewModel

object DestinasiHomeProperti: DestinasiNavigasi {
    override val route = "home properti"
    override val titleRes = "Home Properti"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePropertiView(
    navigateToItemEntry: () -> Unit,
    onDetailClick: (Int) -> Unit = {},
    onEditClick: (Int) -> Unit = {},
    viewModel: HomePropertiViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Properti") },
                actions = {
                    IconButton(onClick = { viewModel.getAllProperti() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToItemEntry) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Properti")
            }
        }
    ) { innerPadding ->
        PropertiStatus(
            homePropertiUiState = viewModel.propertiUiState,
            retryAction = { viewModel.getAllProperti() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onEditClick = onEditClick,
            onDeleteClick = { viewModel.deleteProperti(it.idProperti) }
        )
    }
}

@Composable
fun PropertiStatus(
    homePropertiUiState: HomePropertiUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Properti) -> Unit
) {
    when (homePropertiUiState) {
        is HomePropertiUiState.Loading -> LoadingScreen(modifier)
        is HomePropertiUiState.Success ->
            if (homePropertiUiState.properti.isEmpty()) {
                EmptyScreen(modifier)
            } else {
                PropertiList(
                    propertiList = homePropertiUiState.properti,
                    modifier = modifier,
                    onDetailClick = onDetailClick,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }
        is HomePropertiUiState.Error -> ErrorScreen(retryAction, modifier)
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
        Text(text = "Tidak ada data properti.")
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
fun PropertiList(
    propertiList: List<Properti>,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Properti) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(propertiList) { properti ->
            PropertiCard(
                properti = properti,
                onDetailClick = { onDetailClick(properti.idProperti) },
                onEditClick = { onEditClick(properti.idProperti) },
                onDeleteClick = { onDeleteClick(properti) }
            )
        }
    }
}

@Composable
fun PropertiCard(
    properti: Properti,
    onDetailClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onDetailClick),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = properti.namaProperti, style = MaterialTheme.typography.titleLarge)
            Text(text = "Lokasi: ${properti.lokasi}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Status: ${properti.statusProperti}", style = MaterialTheme.typography.bodyMedium)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Properti")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus Properti")
                }
            }
        }
    }
}