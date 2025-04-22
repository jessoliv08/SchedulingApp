package com.example.schedulingapp.android.view.selectdate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.schedulingapp.ScheduleMeetingViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


@Composable
fun SelectDateView(navController: NavHostController, viewModel: ScheduleMeetingViewModel) {
    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time.toString()
                    viewModel.setSelectedTime(currentTime)
                    navController.navigate("second")
                }) {
                    Text("Go to Next Screen")
                }
            }
        }
    )
}
