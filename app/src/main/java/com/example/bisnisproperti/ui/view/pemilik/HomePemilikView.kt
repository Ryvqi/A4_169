package com.example.bisnisproperti.ui.view.pemilik

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
import com.example.bisnisproperti.model.Pemilik
import com.example.bisnisproperti.navigasi.DestinasiNavigasi
import com.example.bisnisproperti.ui.viewmodel.pemilik.HomePemilikUiState
import com.example.bisnisproperti.ui.viewmodel.pemilik.HomePemilikViewModel
import com.example.bisnisproperti.ui.viewmodel.pemilik.PenyediaVMPemilik

object DestinasiHomePemilik: DestinasiNavigasi {
    override val route = "home pemilik"
    override val titleRes = "Home Pemilik"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePemilikView(
    navigateToItemEntry: () -> Unit,
    onDetailClick: (Int) -> Unit = {},
    onEditClick: (Int) -> Unit = {},
    viewModel: HomePemilikViewModel = viewModel(factory = PenyediaVMPemilik.Factory)
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Pemilik") },
                actions = {
                    IconButton(onClick = { viewModel.getAllPemilik() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToItemEntry) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Pemilik")
            }
        }
    ) { innerPadding ->
        PemilikStatus(
            homePemilikUiState = viewModel.pemilikUiState,
            retryAction = { viewModel.getAllPemilik() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onEditClick = onEditClick,
            onDeleteClick = { viewModel.deletePemilik(it.idPemilik) }
        )
    }
}

@Composable
fun PemilikStatus(
    homePemilikUiState: HomePemilikUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Pemilik) -> Unit
) {
    when (homePemilikUiState) {
        is HomePemilikUiState.Loading -> LoadingScreen(modifier)
        is HomePemilikUiState.Success ->
            if (homePemilikUiState.pemilik.isEmpty()) {
                EmptyScreen(modifier)
            } else {
                PemilikList(
                    pemilikList = homePemilikUiState.pemilik,
                    modifier = modifier,
                    onDetailClick = onDetailClick,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }
        is HomePemilikUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun PemilikList(
    pemilikList: List<Pemilik>,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Pemilik) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pemilikList) { pemilik ->
            PemilikCard(
                pemilik = pemilik,
                onDetailClick = { onDetailClick(pemilik.idPemilik) },
                onEditClick = { onEditClick(pemilik.idPemilik) },
                onDeleteClick = { onDeleteClick(pemilik) }
            )
        }
    }
}

@Composable
fun PemilikCard(
    pemilik: Pemilik,
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
            Text(text = pemilik.namaPemilik, style = MaterialTheme.typography.titleLarge)
            Text(text = "Kontak: ${pemilik.kontakPemilik}", style = MaterialTheme.typography.bodyMedium)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Pemilik")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus Pemilik")
                }
            }
        }
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
        Text(text = "Tidak ada data pemilik.")
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