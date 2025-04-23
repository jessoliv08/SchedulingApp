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
import com.example.schedulingapp.repository.MeetingStorage
import com.example.schedulingapp.usecase.MeetingUseCaseImpl

class MainActivity : ComponentActivity() {
    private val useCase = MeetingUseCaseImpl(MeetingStorage)
    private val selectDateViewModelImpl = SelectDateViewModelImpl()
    private lateinit var meetingInfoViewModel : MeetingInfoViewModelImpl
    private lateinit var selectHourViewModelImpl : SelectHourViewModelImpl
    private lateinit var setupInterviewedViewModelImpl : SetupInterviewedViewModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        selectHourViewModelImpl = SelectHourViewModelImpl(lifecycleScope)
        setupInterviewedViewModelImpl = SetupInterviewedViewModelImpl(lifecycleScope, useCase)
        meetingInfoViewModel = MeetingInfoViewModelImpl(lifecycleScope, useCase)
        super.onCreate(savedInstanceState)
        setContent {
            SchedulingMainView(selectDateViewModelImpl, selectHourViewModelImpl, setupInterviewedViewModelImpl, meetingInfoViewModel)
        }
    }
}
