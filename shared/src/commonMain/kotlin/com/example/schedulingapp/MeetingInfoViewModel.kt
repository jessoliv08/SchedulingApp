package com.example.schedulingapp

interface MeetingInfoViewModel {
    val logo: ImageResource
    val interviewInfo: String
    val timeInfo: IconText
    val callInfo: IconText
//    val calendarInfo: IconText
    val timeZone: IconText
 //   val name: IconText
//    val email: IconText
    val cancelButton: ButtonViewModel
}