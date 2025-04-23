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
import com.example.schedulingapp.repository.AppointmentRepository
import com.example.schedulingapp.repository.MeetingStorage
import com.example.schedulingapp.usecase.AppointmentUseCaseImpl
import com.example.schedulingapp.usecase.MeetingUseCaseImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    private val client =  HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    private val repository = AppointmentRepository(client)
    private val useCase = MeetingUseCaseImpl(MeetingStorage)
    private val appointmentUseCase = AppointmentUseCaseImpl(repository)
    private lateinit var selectDateViewModelImpl : SelectDateViewModelImpl
    private lateinit var meetingInfoViewModel : MeetingInfoViewModelImpl
    private lateinit var selectHourViewModelImpl : SelectHourViewModelImpl
    private lateinit var setupInterviewedViewModelImpl : SetupInterviewedViewModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        selectDateViewModelImpl = SelectDateViewModelImpl(lifecycleScope, appointmentUseCase)
        selectHourViewModelImpl = SelectHourViewModelImpl(lifecycleScope, appointmentUseCase)
        setupInterviewedViewModelImpl = SetupInterviewedViewModelImpl(lifecycleScope, useCase, appointmentUseCase)
        meetingInfoViewModel = MeetingInfoViewModelImpl(lifecycleScope, useCase)
        super.onCreate(savedInstanceState)
        setContent {
            SchedulingMainView(selectDateViewModelImpl, selectHourViewModelImpl, setupInterviewedViewModelImpl, meetingInfoViewModel)
        }
    }
}
