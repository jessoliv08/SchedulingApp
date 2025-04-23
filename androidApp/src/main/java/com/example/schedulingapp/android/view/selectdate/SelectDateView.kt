package com.example.schedulingapp.android.view.selectdate

import android.app.Activity
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.activity.compose.BackHandler
import com.example.schedulingapp.SelectHourViewModel
import com.example.schedulingapp.SelectHourViewModelImpl
import com.example.schedulingapp.android.MyApplicationTheme
import com.example.schedulingapp.android.view.components.calendar.CalendarApp
import com.example.schedulingapp.android.view.components.IconTextView
import com.example.schedulingapp.android.view.components.ListButtonSelectionView

@Composable
fun SelectDateView(
    navController: NavHostController,
    selectDateViewModel: SelectDateViewModel,
    selectHourViewModel : SelectHourViewModel
) {
    val availableDates by selectDateViewModel.availableDate.collectAsState()
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
                                painter = painterResource(id = getImageAssetName(selectDateViewModel.logo)),
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
                            painter = painterResource(id = getImageAssetName(selectDateViewModel.interviewerProfileImage)),
                            contentDescription = "ProfileImage",
                            modifier = Modifier.size(54.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
                        )
                        Text(
                            text = selectDateViewModel.interviewerName,
                            style = MaterialTheme.typography.body2
                        )
                        Text(
                            text = selectDateViewModel.interviewerDetail,
                            style = MaterialTheme.typography.h1
                        )
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth()
                        ) {
                            IconTextView(selectDateViewModel.timeInfo)
                            IconTextView(selectDateViewModel.callInfo)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = selectDateViewModel.selectDateTitle,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // The calendar I found and edited was supported only for api above O
                        // use same list as hour if not
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
                                        val monthData = getMonthInfo(month)
                                        selectDateViewModel.setDateAndTime(monthData.startDateTime, monthData.endDateTime, monthData.label)
                                    }

                                }
                            )
                        } else {
                            ListButtonSelectionView(
                                padding,
                                availableDates
                            ) { availableDate ->
                                availableDate.let {
                                    selectHourViewModel.setSelectedDate(availableDate)
                                }
                                navController.navigate("second")
                            }
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 30.dp, vertical = 20.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = selectDateViewModel.timeZoneTitle,
                            style = MaterialTheme.typography.subtitle2
                        )
                        IconTextView(selectDateViewModel.timeZone)
                    }
                }
            }
        )
    }
    val context = LocalContext.current
    val activity = context as? Activity

    BackHandler {
        activity?.finish()
    }
}

// Preview for light and dark modes
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LightModePreview() {
    SelectDateView(
        navController = rememberNavController(),
        selectDateViewModel = SelectDateViewModelImpl(rememberCoroutineScope()),
        selectHourViewModel = SelectHourViewModelImpl(rememberCoroutineScope())
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview() {
    SelectDateView(
        navController = rememberNavController(),
        selectDateViewModel = SelectDateViewModelImpl(rememberCoroutineScope()),
        selectHourViewModel = SelectHourViewModelImpl(rememberCoroutineScope())
    )
}