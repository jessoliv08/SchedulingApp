package com.example.schedulingapp.android.view.selectdate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.schedulingapp.SelectDateViewModel
import com.example.schedulingapp.SelectDateViewModelImpl
import com.example.schedulingapp.android.getImageAssetName
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import kotlinx.datetime.TimeZone
import androidx.compose.runtime.rememberCoroutineScope
import java.time.YearMonth
import androidx.compose.ui.graphics.ColorFilter
import com.example.schedulingapp.SelectHourViewModel
import com.example.schedulingapp.SelectHourViewModelImpl
import com.example.schedulingapp.android.MyApplicationTheme
import com.example.schedulingapp.android.view.components.CalendarApp
import com.example.schedulingapp.android.view.components.IconTextView
import java.util.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant

@Composable
fun SelectDateView(navController: NavHostController, viewModel: SelectDateViewModel, selectHourViewModel : SelectHourViewModel) {
    val availableDates by viewModel.availableDate.collectAsState()
    MyApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = getImageAssetName(viewModel.logo)),
                                contentDescription = "App Title",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                )
            },
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(bottom = 100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Image(
                            painter = painterResource(id = getImageAssetName(viewModel.interviewerProfileImage)),
                            contentDescription = "ProfileImage",
                            modifier = Modifier.size(54.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
                        )
                        Text(
                            text = viewModel.interviewerName,
                            style = MaterialTheme.typography.body2
                        )
                        Text(
                            text = viewModel.interviewerDetail,
                            style = MaterialTheme.typography.h1
                        )
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth()
                        ) {
                            IconTextView(viewModel.timeInfo)
                            IconTextView(viewModel.callInfo)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = viewModel.selectDateTitle,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            CalendarApp(availableDates = availableDates,
                                onDateSelect = { availableDate ->
                                    availableDate?.let {
                                        selectHourViewModel.setSelectedDate(availableDate)
                                    }
                                    navController.navigate("second")
                                },
                                onMonthUpdate = {
                                    it?.let { month ->
                                        val monthdata = getMonthInfo(month)
                                        viewModel.setDateAndTime(monthdata.startDateTime, monthdata.endDateTime, monthdata.label)
                                    }

                                }
                            )
                        }

                    }
                    // Fixed bottom content
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 30.dp, vertical = 20.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = viewModel.timeZoneTitle,
                            style = MaterialTheme.typography.subtitle2
                        )
                        IconTextView(viewModel.timeZone)
                    }
                }
            }
        )
    }
}

data class MonthInfo(
    val startDateTime: String,
    val endDateTime: String,
    val label: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun getMonthInfo(yearMonth: YearMonth): MonthInfo {
    val zone = TimeZone.currentSystemDefault()

    val start = LocalDate(yearMonth.year, yearMonth.monthValue, 1)
        .atTime(LocalTime(hour = 0, minute = 0, second = 0))
        .toInstant(zone)
        .toString()

    val yearMonthEnd = yearMonth.atEndOfMonth()
    val endOfMonth = LocalDate(yearMonthEnd.year, yearMonthEnd.monthValue, yearMonthEnd.dayOfMonth)
        .atTime(LocalTime(hour = 23, minute = 59, second = 59))
        .toInstant(zone)
        .toString()

    val label = YearMonth.of(yearMonth.year, yearMonth.monthValue)
        .month.getDisplayName(java.time.format.TextStyle.SHORT, Locale.ENGLISH) + "${yearMonth.year}"

    return MonthInfo(start, endOfMonth, label)
}

// Preview for light and dark modes
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LightModePreview() {
    SelectDateView(
        navController = rememberNavController(),
        viewModel = SelectDateViewModelImpl(rememberCoroutineScope()),
        selectHourViewModel = SelectHourViewModelImpl(rememberCoroutineScope())
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview() {
    SelectDateView(
        navController = rememberNavController(),
        viewModel = SelectDateViewModelImpl(rememberCoroutineScope()),
        selectHourViewModel = SelectHourViewModelImpl(rememberCoroutineScope())
    )
}