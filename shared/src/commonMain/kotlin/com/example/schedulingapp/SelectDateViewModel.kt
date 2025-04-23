package com.example.schedulingapp

import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

interface SelectDateViewModel {
    val logo: ImageResource
    val interviewerName: String
    val interviewerDetail: String
    val interviewerProfileImage: ImageResource
    val timeInfo: IconText
    val callInfo: IconText
    val timeZoneTitle: String
    val timeZone: IconText
    val selectDateTitle: String
    val availableDate: StateFlow<List<LocalDate>>
    fun setDateAndTime(date: String, endDate: String, month: String)
}