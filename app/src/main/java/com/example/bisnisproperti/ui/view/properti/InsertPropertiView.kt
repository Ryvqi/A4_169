package com.example.bisnisproperti.ui.view.properti

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bisnisproperti.ui.viewmodel.properti.InsertPropertiUiEvent
import com.example.bisnisproperti.ui.viewmodel.properti.InsertPropertiUiState
import com.example.bisnisproperti.ui.viewmodel.properti.InsertPropertiViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertPropertiView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPropertiViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Tambah Properti") },
                navigationIcon = {
                    Button(onClick = navigateBack) {
                        Text("Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        InsertPropertiBody(
            uiState = viewModel.uiState,
            onPropertiValueChange = viewModel::updateInsertPropertiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertProperti()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun InsertPropertiBody(
    uiState: InsertPropertiUiState,
    onPropertiValueChange: (InsertPropertiUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputProperti(
            insertUiEvent = uiState.insertPropertiUiEvent,
            onValueChange = onPropertiValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan Properti")
        }
    }
}

@Composable
fun FormInputProperti(
    insertUiEvent: InsertPropertiUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPropertiUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.namaProperti,
            onValueChange = { onValueChange(insertUiEvent.copy(namaProperti = it)) },
            label = { Text("Nama Properti") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsiProperti,
            onValueChange = { onValueChange(insertUiEvent.copy(deskripsiProperti = it)) },
            label = { Text("Deskripsi Properti") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.lokasi,
            onValueChange = { onValueChange(insertUiEvent.copy(lokasi = it)) },
            label = { Text("Lokasi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.harga,
            onValueChange = { onValueChange(insertUiEvent.copy(harga = it)) },
            label = { Text("Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.statusProperti,
            onValueChange = { onValueChange(insertUiEvent.copy(statusProperti = it)) },
            label = { Text("Status Properti") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idJenis.toString(),
            onValueChange = { onValueChange(insertUiEvent.copy(idJenis = it.toIntOrNull() ?: 0)) },
            label = { Text("ID Jenis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idPemilik.toString(),
            onValueChange = { onValueChange(insertUiEvent.copy(idPemilik = it.toIntOrNull() ?: 0)) },
            label = { Text("ID Pemilik") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idManajer.toString(),
            onValueChange = { onValueChange(insertUiEvent.copy(idManajer = it.toIntOrNull() ?: 0)) },
            label = { Text("ID Manajer") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = "Isi semua data dengan lengkap",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}