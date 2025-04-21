package com.example.schedulingapp.android.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schedulingapp.android.MyApplicationTheme
import com.example.schedulingapp.android.view.selectdate.SelectDateView
import com.example.schedulingapp.android.view.selecthour.SelectHourView
import com.example.schedulingapp.viewmodel.ScheduleMeetingViewModel


@Composable
fun SchedulingMainView(viewModel : ScheduleMeetingViewModel) {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "first") {
                composable("first") { SelectDateView(navController, viewModel) }
                composable("second") { SelectHourView(navController, viewModel) }
            }
        }
    }
}
