package com.example.schedulingapp.android.view.selecthour

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.schedulingapp.android.R
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.schedulingapp.SelectHourViewModel
import com.example.schedulingapp.SelectHourViewModelImpl
import com.example.schedulingapp.SetupInterviewedViewModel
import com.example.schedulingapp.SetupInterviewedViewModelImpl
import com.example.schedulingapp.android.MyApplicationTheme
import com.example.schedulingapp.android.view.components.IconTextView
import kotlinx.datetime.LocalDate

@Composable
fun SelectHourView(
    navController: NavHostController,
    viewModel : SelectHourViewModel,
    setupInterviewedViewModel: SetupInterviewedViewModel
) {
    val selectedDate by viewModel.dateSelected.collectAsState()
    val availableTimes by viewModel.availableTime.collectAsState()

    MyApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        selectedDate?.let {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(formatLocalDate(it))
                            }
                        }
                            },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth()
                    ) {
                        Text(text = viewModel.timeZoneTitle, style = MaterialTheme.typography.subtitle2)
                        IconTextView(viewModel.timeZone)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = viewModel.selectHourTitle,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = viewModel.selectHourDescription,
                        style = MaterialTheme.typography.body2
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(padding)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        for (availableDate in availableTimes) {
                            Button(
                                onClick = {
                                    selectedDate?.let {
                                        setupInterviewedViewModel.setDateAndTime(
                                            it,
                                            availableDate
                                        )
                                    }
                                    navController.navigate("third")
                                },
                                shape = RoundedCornerShape(8.dp),
                                elevation = null,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = MaterialTheme.colors.primary
                                ),
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
                                    .border(
                                        1.dp,
                                        MaterialTheme.colors.primary.copy(alpha = 0.7f),
                                        RoundedCornerShape(8.dp)
                                    )
                            ) {
                                Text(availableDate.toString())
                            }
                        }
                    }
                }
            }
        )
    }
}

fun formatLocalDate(date: LocalDate): String {
    val dayOfWeek = date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() } // Thursday
    val month = date.month.name.lowercase().replaceFirstChar { it.uppercase() }          // April
    val day = date.dayOfMonth                                                              // 23
    val year = date.year                                                                   // 2025
    return "$dayOfWeek $month $day, $year"
}


// Preview for light and dark modes
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LightModePreview() {
    SelectHourView(
        navController = rememberNavController(),
        viewModel = SelectHourViewModelImpl(rememberCoroutineScope()
        ).apply {
            setSelectedDate(LocalDate.parse("2025-04-21"))
    },
        setupInterviewedViewModel = SetupInterviewedViewModelImpl(rememberCoroutineScope()))
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview() {
    SelectHourView(
        navController = rememberNavController(),
        viewModel = SelectHourViewModelImpl(rememberCoroutineScope()
        ).apply {
            setSelectedDate(LocalDate.parse("2025-04-21"))
        },
        setupInterviewedViewModel = SetupInterviewedViewModelImpl(rememberCoroutineScope()))
}