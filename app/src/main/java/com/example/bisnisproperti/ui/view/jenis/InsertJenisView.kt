package com.example.bisnisproperti.ui.view.jenis

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
import com.example.bisnisproperti.ui.viewmodel.jenis.InsertJenisUiEvent
import com.example.bisnisproperti.ui.viewmodel.jenis.InsertJenisUiState
import com.example.bisnisproperti.ui.viewmodel.jenis.InsertJenisViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertJenisView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertJenisViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Tambah Jenis Properti") },
                navigationIcon = {
                    Button(onClick = navigateBack) {
                        Text("Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        InsertJenisBody(
            uiState = viewModel.uiState,
            onJenisValueChange = viewModel::updateInsertJenisState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertJenisProperti()
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
fun InsertJenisBody(
    uiState: InsertJenisUiState,
    onJenisValueChange: (InsertJenisUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputJenis(
            insertUiEvent = uiState.insertJenisUiEvent,
            onValueChange = onJenisValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan Jenis Properti")
        }
    }
}

@Composable
fun FormInputJenis(
    insertUiEvent: InsertJenisUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertJenisUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.namaJenis,
            onValueChange = { onValueChange(insertUiEvent.copy(namaJenis = it)) },
            label = { Text("Nama Jenis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsiJenis,
            onValueChange = { onValueChange(insertUiEvent.copy(deskripsiJenis = it)) },
            label = { Text("Deskripsi Jenis") },
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