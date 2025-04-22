package com.example.schedulingapp.android.view.selecthour

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.schedulingapp.android.R
import androidx.compose.runtime.*
import com.example.schedulingapp.ScheduleMeetingViewModel

@Composable
fun SelectHourView(navController: NavHostController, viewModel: ScheduleMeetingViewModel) {
    val selectedTime by viewModel.selectedTime.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Back") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                            contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Selected Time: ${selectedTime ?: "No time selected"}")
            }
        }
    )
}