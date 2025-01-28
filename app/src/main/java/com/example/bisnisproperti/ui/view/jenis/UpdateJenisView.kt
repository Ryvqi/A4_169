package com.example.bisnisproperti.ui.view.jenis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bisnisproperti.navigasi.DestinasiNavigasi
import com.example.bisnisproperti.ui.viewmodel.jenis.InsertJenisUiEvent
import com.example.bisnisproperti.ui.viewmodel.jenis.InsertJenisUiState
import com.example.bisnisproperti.ui.viewmodel.jenis.PenyediaVMJenis
import com.example.bisnisproperti.ui.viewmodel.jenis.UpdateJenisViewModel
import kotlinx.coroutines.launch

object DestinasiUpdateJenis: DestinasiNavigasi {
    override val route = "update jenis"
    override val titleRes = "UpdateJenis"
    const val idJenis = "idjenis"
    val routeWithArg = "$route/{$idJenis}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateJenisView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateJenisViewModel = viewModel(factory = PenyediaVMJenis.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Update Jenis") },
                navigationIcon = {
                    Button(onClick = navigateBack) {
                        Text("Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        UpdateJenisBody(
            uiState = viewModel.uiState,
            onJenisValueChange = viewModel::updateState,
            onUpdateClick = {
                coroutineScope.launch {
                    viewModel.updateJenis()
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
fun UpdateJenisBody(
    uiState: InsertJenisUiState,
    onJenisValueChange: (InsertJenisUiEvent) -> Unit,
    onUpdateClick: () -> Unit,
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
            onClick = onUpdateClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update Jenis")
        }
    }
}