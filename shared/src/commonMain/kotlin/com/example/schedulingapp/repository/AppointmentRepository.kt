package com.example.schedulingapp.repository

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class AppointmentRepository(
    private val client: HttpClient
) {
    suspend fun getAvailableTimes(
        startDateTime: String,
        endDateTime: String,
        mockResponseName: String
    ): List<String> {
        try {
            val url =
                "https://5b94bbb0-4b84-4173-8753-c9b46c84fc76.mock.pstmn.io/appointment_availabilities/available_times"

            val response: AvailableTimesResponse = client.get(url) {
                parameter("start_date_time", startDateTime)
                parameter("end_date_time", endDateTime)
                header("x-mock-response-name", mockResponseName)
            }.body()

            return response.data.available_times
        } catch (ex: Exception) {
            return emptyList()
        }
    }
}
