package com.example.bisnisproperti.ui.view.manajer

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
import com.example.bisnisproperti.ui.viewmodel.manajer.InsertManajerUiEvent
import com.example.bisnisproperti.ui.viewmodel.manajer.InsertManajerUiState
import com.example.bisnisproperti.ui.viewmodel.manajer.InsertManajerViewModel
import com.example.bisnisproperti.ui.viewmodel.manajer.PenyediaVMManajer
import kotlinx.coroutines.launch

object DestinasiInsertManajer: DestinasiNavigasi {
    override val route = "insert manajer"
    override val titleRes = "Insert Manajer"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertManajerView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertManajerViewModel = viewModel(factory = PenyediaVMManajer.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Tambah Manajer") },
                navigationIcon = {
                    Button(onClick = navigateBack) {
                        Text("Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        InsertManajerBody(
            uiState = viewModel.uiState,
            onManajerValueChange = viewModel::updateInsertManajerState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertManajer()
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
fun InsertManajerBody(
    uiState: InsertManajerUiState,
    onManajerValueChange: (InsertManajerUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputManajer(
            insertUiEvent = uiState.insertManajerUiEvent,
            onValueChange = onManajerValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan Manajer")
        }
    }
}

@Composable
fun FormInputManajer(
    insertUiEvent: InsertManajerUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertManajerUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.namaManajer,
            onValueChange = { onValueChange(insertUiEvent.copy(namaManajer = it)) },
            label = { Text("Nama Manajer") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.kontakManajer,
            onValueChange = { onValueChange(insertUiEvent.copy(kontakManajer = it)) },
            label = { Text("Kontak Manajer") },
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