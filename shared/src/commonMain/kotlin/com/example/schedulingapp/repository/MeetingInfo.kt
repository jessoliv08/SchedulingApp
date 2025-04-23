package com.example.schedulingapp.repository

import kotlinx.serialization.Serializable

@Serializable
data class MeetingInfo(
    val name: String,
    val email: String,
    val dateTime: String
)
