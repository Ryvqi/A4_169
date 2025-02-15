package com.example.bisnisproperti.ui.view.manajer

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
import com.example.bisnisproperti.ui.viewmodel.manajer.InsertManajerUiEvent
import com.example.bisnisproperti.ui.viewmodel.manajer.InsertManajerUiState
import com.example.bisnisproperti.ui.viewmodel.manajer.PenyediaVMManajer
import com.example.bisnisproperti.ui.viewmodel.manajer.UpdateManajerViewModel
import kotlinx.coroutines.launch

object DestinasiUpdateManajer: DestinasiNavigasi {
    override val route = "update manajer"
    override val titleRes = "Update Manajer"
    const val idManajer = "idmanajer"
    val routeWithArg = "$route/{$idManajer}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateManajerView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateManajerViewModel = viewModel(factory = PenyediaVMManajer.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Update Manajer Properti") },
                navigationIcon = {
                    Button(onClick = navigateBack) {
                        Text("Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        UpdateManajerBody(
            uiState = viewModel.uiState,
            onManajerValueChange = viewModel::updateState,
            onUpdateClick = {
                coroutineScope.launch {
                    viewModel.updateManajer()
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
fun UpdateManajerBody(
    uiState: InsertManajerUiState,
    onManajerValueChange: (InsertManajerUiEvent) -> Unit,
    onUpdateClick: () -> Unit,
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
            onClick = onUpdateClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update Manajer")
        }
    }
}