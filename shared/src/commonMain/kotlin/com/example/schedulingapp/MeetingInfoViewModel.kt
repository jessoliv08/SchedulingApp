package com.example.schedulingapp

import kotlinx.coroutines.flow.StateFlow

interface MeetingInfoViewModel {
    val logo: ImageResource
    val interviewInfo: String
    val timeInfo: IconText
    val callInfo: IconText
    val calendarInfo: StateFlow<IconText?>
    val timeZone: IconText
    val name: StateFlow<IconText?>
    val email: StateFlow<IconText?>
    val cancelButton: ButtonViewModel
}