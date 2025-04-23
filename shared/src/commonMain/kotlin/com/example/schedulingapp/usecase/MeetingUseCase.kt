package com.example.schedulingapp.usecase

import com.example.schedulingapp.repository.MeetingInfo
import kotlinx.coroutines.flow.Flow

interface MeetingUseCase {
    fun saveMeetingDate(
        name: String,
        email: String,
        dateAndTime: String
    )

    fun getMeetingDate(): Flow<MeetingInfo?>

    fun clearMeetingDate()
}