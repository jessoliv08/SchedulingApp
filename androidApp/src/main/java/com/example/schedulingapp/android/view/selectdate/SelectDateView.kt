package com.example.schedulingapp.android.view.selectdate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.ColorFilter
import com.example.schedulingapp.SelectHourViewModel
import com.example.schedulingapp.SelectHourViewModelImpl
import com.example.schedulingapp.android.MyApplicationTheme
import com.example.schedulingapp.android.view.components.IconTextView

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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
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
                    Text(text = viewModel.interviewerName, style = MaterialTheme.typography.body2)
                    Text(text = viewModel.interviewerDetail, style = MaterialTheme.typography.h1)
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
                    for (availableDate in availableDates) {
                        Button(onClick = {
                            selectHourViewModel.setSelectedDate(availableDate)
                            navController.navigate("second")
                        },
                            shape = RoundedCornerShape(8.dp),
                            elevation = null,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = MaterialTheme.colors.primary
                            ),
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
                                .border(1.dp, MaterialTheme.colors.primary.copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                        ) {
                            Text(availableDate.toString())
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth()
                    ) {
                        Text(text = viewModel.timeZoneTitle, style = MaterialTheme.typography.subtitle2)
                        IconTextView(viewModel.timeZone)
                    }
                }
            }
        )
    }
}


// Preview for light and dark modes
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LightModePreview() {
    SelectDateView(
        navController = rememberNavController(),
        viewModel = SelectDateViewModelImpl(),
        selectHourViewModel = SelectHourViewModelImpl(rememberCoroutineScope())
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview() {
    SelectDateView(
        navController = rememberNavController(),
        viewModel = SelectDateViewModelImpl(),
        selectHourViewModel = SelectHourViewModelImpl(rememberCoroutineScope())
    )
}