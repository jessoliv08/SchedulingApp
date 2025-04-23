package com.example.schedulingapp.android.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schedulingapp.MeetingInfoViewModel
import com.example.schedulingapp.SelectDateViewModel
import com.example.schedulingapp.SelectHourViewModel
import com.example.schedulingapp.SetupInterviewedViewModel
import com.example.schedulingapp.android.MyApplicationTheme
import com.example.schedulingapp.android.view.interviewed.SetupInterviewedView
import com.example.schedulingapp.android.view.meeting.MeetingInfoView
import com.example.schedulingapp.android.view.selectdate.SelectDateView
import com.example.schedulingapp.android.view.selecthour.SelectHourView


@Composable
fun SchedulingMainView(
    viewModel : SelectDateViewModel,
    selectHourViewModel : SelectHourViewModel,
    setupInterviewedViewModel: SetupInterviewedViewModel,
    meetingInfoViewModel: MeetingInfoViewModel
) {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "first") {
                composable("first") { SelectDateView(navController, viewModel, selectHourViewModel) }
                composable("second") { SelectHourView(navController, selectHourViewModel, setupInterviewedViewModel) }
                composable("third") { SetupInterviewedView(navController, setupInterviewedViewModel) }
                composable("fourth") { MeetingInfoView(navController, meetingInfoViewModel) }
            }
        }
    }
}
