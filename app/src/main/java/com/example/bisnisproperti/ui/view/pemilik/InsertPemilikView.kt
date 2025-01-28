package com.example.bisnisproperti.ui.view.pemilik

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bisnisproperti.navigasi.DestinasiNavigasi
import com.example.bisnisproperti.ui.viewmodel.pemilik.InsertPemilikUiEvent
import com.example.bisnisproperti.ui.viewmodel.pemilik.InsertPemilikUiState
import com.example.bisnisproperti.ui.viewmodel.pemilik.InsertPemilikViewModel
import kotlinx.coroutines.launch

object DestinasiInsertPemilik: DestinasiNavigasi {
    override val route = "insert pemilik"
    override val titleRes = "Insert Pemilik"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertPemilikView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPemilikViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Tambah Pemilik") },
                navigationIcon = {
                    Button(onClick = navigateBack) {
                        Text("Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        InsertPemilikBody(
            uiState = viewModel.uiState,
            onPemilikValueChange = viewModel::updateInsertPemilikState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertPemilik()
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
fun InsertPemilikBody(
    uiState: InsertPemilikUiState,
    onPemilikValueChange: (InsertPemilikUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPemilik(
            insertUiEvent = uiState.insertPemilikUiEvent,
            onValueChange = onPemilikValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan Pemilik")
        }
    }
}

@Composable
fun FormInputPemilik(
    insertUiEvent: InsertPemilikUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPemilikUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.namaPemilik,
            onValueChange = { onValueChange(insertUiEvent.copy(namaPemilik = it)) },
            label = { Text("Nama Pemilik") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.kontakPemilik,
            onValueChange = { onValueChange(insertUiEvent.copy(kontakPemilik = it)) },
            label = { Text("Kontak Pemilik") },
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