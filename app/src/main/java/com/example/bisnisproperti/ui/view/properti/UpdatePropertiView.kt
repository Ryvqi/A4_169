package com.example.bisnisproperti.ui.view.properti

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
import com.example.bisnisproperti.ui.viewmodel.properti.InsertPropertiUiEvent
import com.example.bisnisproperti.ui.viewmodel.properti.InsertPropertiUiState
import com.example.bisnisproperti.ui.viewmodel.properti.PenyediaVMProperti
import com.example.bisnisproperti.ui.viewmodel.properti.UpdatePropertiViewModel
import kotlinx.coroutines.launch

object DestinasiUpdateProperti: DestinasiNavigasi {
    override val route = "update properti"
    override val titleRes = "Update Properti"
    const val idProperti = "idproperti"
    val routeWithArg = "$route/{$idProperti}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePropertiView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdatePropertiViewModel = viewModel(factory = PenyediaVMProperti.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Update Properti") },
                navigationIcon = {
                    Button(onClick = navigateBack) {
                        Text("Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        UpdatePropertiBody(
            uiState = viewModel.uiState,
            onPropertiValueChange = viewModel::updateState,
            onUpdateClick = {
                coroutineScope.launch {
                    viewModel.updateProperti()
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
fun UpdatePropertiBody(
    uiState: InsertPropertiUiState,
    onPropertiValueChange: (InsertPropertiUiEvent) -> Unit,
    onUpdateClick: () -> Unit,
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
            onClick = onUpdateClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update Properti")
        }
    }
}