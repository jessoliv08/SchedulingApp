package com.example.schedulingapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.schedulingapp.android.view.SchedulingMainView
import com.example.schedulingapp.viewmodel.ScheduleMeetingViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = ScheduleMeetingViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchedulingMainView(viewModel)
        }
    }
}
