package com.example.schedulingapp.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableTimesResponse(
    @SerialName("data")
    val data: AvailableTimesData
)

@Serializable
data class AvailableTimesData(
    @SerialName("available_times")
    val available_times: List<String>
)
