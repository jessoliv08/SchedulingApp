package com.example.schedulingapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.schedulingapp.MeetingInfoViewModelImpl
import com.example.schedulingapp.SelectDateViewModelImpl
import com.example.schedulingapp.SelectHourViewModelImpl
import com.example.schedulingapp.SetupInterviewedViewModelImpl
import com.example.schedulingapp.android.view.SchedulingMainView

class MainActivity : ComponentActivity() {
    private val selectDateViewModelImpl = SelectDateViewModelImpl()
    private val meetingInfoViewModel = MeetingInfoViewModelImpl()
    private lateinit var selectHourViewModelImpl : SelectHourViewModelImpl
    private lateinit var setupInterviewedViewModelImpl : SetupInterviewedViewModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        selectHourViewModelImpl = SelectHourViewModelImpl(lifecycleScope)
        setupInterviewedViewModelImpl = SetupInterviewedViewModelImpl(lifecycleScope)
        super.onCreate(savedInstanceState)
        setContent {
            SchedulingMainView(selectDateViewModelImpl, selectHourViewModelImpl, setupInterviewedViewModelImpl, meetingInfoViewModel)
        }
    }
}
