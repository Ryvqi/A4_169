package com.example.bisnisproperti.ui.view.properti

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bisnisproperti.ui.viewmodel.properti.InsertPropertiUiEvent
import com.example.bisnisproperti.ui.viewmodel.properti.InsertPropertiUiState

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