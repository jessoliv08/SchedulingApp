package com.example.schedulingapp.android.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> ListButtonSelectionView(
    padding: PaddingValues,
    availableItems: List<T>,
    onClickAction: (T) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        for (availableItem in availableItems) {
            Button(
                onClick = {
                    onClickAction(availableItem)
                },
                shape = RoundedCornerShape(8.dp),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.primary
                ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
                    .border(
                        1.dp,
                        MaterialTheme.colors.primary.copy(alpha = 0.7f),
                        RoundedCornerShape(8.dp)
                    )
            ) {
                Text(availableItem.toString())
            }
        }
    }
}