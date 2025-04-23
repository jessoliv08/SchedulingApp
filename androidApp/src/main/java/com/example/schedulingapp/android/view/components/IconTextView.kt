package com.example.schedulingapp.android.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.schedulingapp.IconText
import com.example.schedulingapp.android.getImageAssetName

@Composable
fun IconTextView(iconImage: IconText) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Image(
            painter = painterResource(id = getImageAssetName(iconImage.icon)),
            contentDescription = "icon",
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.Gray)
        )
        Text(text = iconImage.text, style = MaterialTheme.typography.body2, color = Color.Gray)
    }
}