package com.example.bisnisproperti.ui.view.pemilik

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
import com.example.bisnisproperti.ui.viewmodel.pemilik.InsertPemilikUiEvent
import com.example.bisnisproperti.ui.viewmodel.pemilik.InsertPemilikUiState
import com.example.bisnisproperti.ui.viewmodel.pemilik.PenyediaVMPemilik
import com.example.bisnisproperti.ui.viewmodel.pemilik.UpdatePemilikViewModel
import kotlinx.coroutines.launch

object DestinasiUpdatePemilik: DestinasiNavigasi {
    override val route = "update pemilik"
    override val titleRes = "Update Pemilik"
    const val idPemilik = "idpemilik"
    val routeWithArg = "$route/{$idPemilik}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePemilikView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdatePemilikViewModel = viewModel(factory = PenyediaVMPemilik.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Update Pemilik Properti") },
                navigationIcon = {
                    Button(onClick = navigateBack) {
                        Text("Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        UpdatePemilikBody(
            uiState = viewModel.uiState,
            onPemilikValueChange = viewModel::updateState,
            onUpdateClick = {
                coroutineScope.launch {
                    viewModel.updatePemilik()
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
fun UpdatePemilikBody(
    uiState: InsertPemilikUiState,
    onPemilikValueChange: (InsertPemilikUiEvent) -> Unit,
    onUpdateClick: () -> Unit,
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
            onClick = onUpdateClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update Pemilik")
        }
    }
}