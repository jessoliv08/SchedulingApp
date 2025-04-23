package com.example.schedulingapp

import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

interface SetupInterviewedViewModel {
    val logo: ImageResource
    val interviewInfo: String
    val timeInfo: IconText
    val callInfo: IconText
    val selectDateAndTime: StateFlow<IconText?>
    fun setDateAndTime(date: LocalDate, time: LocalTime)
    val timeZone: IconText
    val enterDetails: String
    val nameLabel: String
    val nameField: StateFlow<String>
    fun onNameChanged(newValue: String)
    val emailLabel: String
    val emailField: StateFlow<String>
    fun onEmailChanged(newValue: String)
    val descriptionAgree: String
    val confirmButton: ButtonViewModel
}