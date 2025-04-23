package com.example.schedulingapp.usecase

import com.example.schedulingapp.repository.MeetingInfo
import com.example.schedulingapp.repository.MeetingStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MeetingUseCaseImpl(private val meetingRepository: MeetingStorage): MeetingUseCase {
    override fun saveMeetingDate(name: String, email: String, dateAndTime: String) {
        meetingRepository.saveMeeting(MeetingInfo(
            name, email, dateAndTime
        ))
    }

    override fun getMeetingDate(): Flow<MeetingInfo?> {
        return flow {
            emit(meetingRepository.getMeeting())
        }
    }

    override fun clearMeetingDate() {
        meetingRepository.clearMeeting()
    }
}