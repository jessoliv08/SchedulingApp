package com.example.schedulingapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class SetupInterviewedViewModelImpl(scope: CoroutineScope): SetupInterviewedViewModel {
    override val logo: ImageResource = ImageResource.LOGO
    override val interviewInfo = "30 Minute Interview"
    override val timeInfo = IconText(
        icon = ImageResource.CLOCK_ICON,
        text = "30 min"
    )
    override val callInfo= IconText(
        icon = ImageResource.MEETING_ICON,
        text = "Web conference details provided upon confirmation"
    )
    override val timeZone = IconText(
        icon = ImageResource.PLANET_ICON,
        text = "Eastern Time - US & Canada"
    )
    override val enterDetails = "Enter Details"
    override val nameLabel = "Name *"
    override val nameField = MutableStateFlow("")
    override fun onNameChanged(newValue: String) {
        nameField.value = newValue
    }
    override val emailLabel = "Email *"
    override val emailField = MutableStateFlow("")
    override fun onEmailChanged(newValue: String) {
        emailField.value = newValue
    }
    override val descriptionAgree = "By proceeding, you confirm that you have read and agree to Calendly´s Terms of Use and Privacy Notice"
    private val _dateSelected = MutableStateFlow<LocalDateTime?>(null)
    private val dateSelected: StateFlow<LocalDateTime?> = _dateSelected.asStateFlow()

    override fun setDateAndTime(date: LocalDate, time: LocalTime) {
        _dateSelected.value = LocalDateTime(date.year, date.monthNumber, date.dayOfMonth, time.hour, time.minute, time.second)
    }

    override val selectDateAndTime = dateSelected.mapNotNull {
        it?.let { localDateAndTime ->
            IconText(
                icon = ImageResource.CALENDAR_ICON,
                text = formatDateTimeRange(localDateAndTime)
            )
        }
    }.stateIn(scope, SharingStarted.Lazily, null)

    override val confirmButton = object : ButtonViewModel {
        override fun action() {
            // save date, hour, name and email
        }

        override val state = nameField.combine(emailField) { name, email ->
            ButtonViewModel.State(
                enable = name.isNotBlank() && email.isNotBlank(),
                title = "Schedule Event"
            )
        }.stateIn(scope, SharingStarted.Lazily, null)
    }

    private fun formatDateTimeRange(startDateTime: LocalDateTime): String {
        val endDateTime = startDateTime
            .toInstant(TimeZone.currentSystemDefault())
            .plus(30, DateTimeUnit.MINUTE, TimeZone.currentSystemDefault())
            .toLocalDateTime(TimeZone.currentSystemDefault())

        val startHour = formatTime(startDateTime)
        val endHour = formatTime(endDateTime)

        val dayOfWeek = startDateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        val month = startDateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }
        val day = startDateTime.dayOfMonth
        val year = startDateTime.year

        return "$startHour - $endHour, $dayOfWeek, $month $day, $year"
    }

    private fun formatTime(dateTime: LocalDateTime): String {
        val hour = dateTime.hour
        val minute = dateTime.minute
        val isAm = hour < 12
        val hour12 = if (hour % 12 == 0) 12 else hour % 12
        val minutePadded = minute.toString().padStart(2, '0')
        val suffix = if (isAm) "am" else "pm"
        return "$hour12:$minutePadded$suffix"
    }

}