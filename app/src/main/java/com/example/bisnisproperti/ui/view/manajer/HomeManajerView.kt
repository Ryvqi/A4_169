package com.example.bisnisproperti.ui.view.manajer

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
import com.example.bisnisproperti.model.ManajerProperti
import com.example.bisnisproperti.navigasi.DestinasiNavigasi
import com.example.bisnisproperti.ui.viewmodel.manajer.HomeManajerUiState
import com.example.bisnisproperti.ui.viewmodel.manajer.HomeManajerViewModel

object DestinasiHomeManajer: DestinasiNavigasi {
    override val route = "home manajer"
    override val titleRes = "Home Manajer"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeManajerView(
    navigateToItemEntry: () -> Unit,
    onDetailClick: (Int) -> Unit = {},
    onEditClick: (Int) -> Unit = {},
    viewModel: HomeManajerViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Manajer") },
                actions = {
                    IconButton(onClick = { viewModel.getAllManajer() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToItemEntry) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Manajer")
            }
        }
    ) { innerPadding ->
        ManajerStatus(
            homeManajerUiState = viewModel.manajerUiState,
            retryAction = { viewModel.getAllManajer() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onEditClick = onEditClick,
            onDeleteClick = { viewModel.deleteManajer(it.idManajer) }
        )
    }
}

@Composable
fun ManajerStatus(
    homeManajerUiState: HomeManajerUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (ManajerProperti) -> Unit
) {
    when (homeManajerUiState) {
        is HomeManajerUiState.Loading -> LoadingScreen(modifier)
        is HomeManajerUiState.Success ->
            if (homeManajerUiState.manajer.isEmpty()) {
                EmptyScreen(modifier)
            } else {
                ManajerList(
                    manajerList = homeManajerUiState.manajer,
                    modifier = modifier,
                    onDetailClick = onDetailClick,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }
        is HomeManajerUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun ManajerList(
    manajerList: List<ManajerProperti>,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (ManajerProperti) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(manajerList) { manajer ->
            ManajerCard(
                manajer = manajer,
                onDetailClick = { onDetailClick(manajer.idManajer) },
                onEditClick = { onEditClick(manajer.idManajer) },
                onDeleteClick = { onDeleteClick(manajer) }
            )
        }
    }
}

@Composable
fun ManajerCard(
    manajer: ManajerProperti,
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
            Text(text = manajer.namaManajer, style = MaterialTheme.typography.titleLarge)
            Text(text = "Kontak: ${manajer.kontakManajer}", style = MaterialTheme.typography.bodyMedium)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Manajer")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus Manajer")
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
        Text(text = "Tidak ada data manajer.")
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