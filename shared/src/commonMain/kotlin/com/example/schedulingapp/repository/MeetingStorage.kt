package com.example.schedulingapp.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.serialization.json.Json

object MeetingStorage {
    private val settings = Settings()

    fun saveMeeting(meeting: MeetingInfo) {
        val json = Json.encodeToString(MeetingInfo.serializer(), meeting)
        settings[MEETING_KEY] = json
    }

    fun getMeeting(): MeetingInfo? {
        val json = settings.getStringOrNull(MEETING_KEY) ?: return null
        return Json.decodeFromString(json)
    }

    fun clearMeeting() {
        settings.remove(MEETING_KEY)
    }

    private const val MEETING_KEY = "meeting_info"
}