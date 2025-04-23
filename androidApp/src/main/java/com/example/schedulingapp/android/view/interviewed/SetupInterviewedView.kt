package com.example.schedulingapp.android.view.interviewed

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.schedulingapp.SetupInterviewedViewModel
import com.example.schedulingapp.android.MyApplicationTheme
import com.example.schedulingapp.android.getImageAssetName
import com.example.schedulingapp.android.view.components.IconTextView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schedulingapp.SetupInterviewedViewModelImpl
import com.example.schedulingapp.android.R
import androidx.compose.material.TextField
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@Composable
fun SetupInterviewedView(
    navController: NavHostController,
    viewModel: SetupInterviewedViewModel
) {
    val dateAndTimeSelectInfo by viewModel.selectDateAndTime.collectAsState()
    val field1 by viewModel.nameField.collectAsState()
    val field2 by viewModel.emailField.collectAsState()
    val button by viewModel.confirmButton.state.collectAsState()
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
                    Text(text = viewModel.interviewInfo, style = MaterialTheme.typography.h1)
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth()
                    ) {
                        IconTextView(viewModel.timeInfo)
                        IconTextView(viewModel.callInfo)
                        dateAndTimeSelectInfo?.let { IconTextView(it) }
                        IconTextView(viewModel.timeZone)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = viewModel.enterDetails,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(text = viewModel.nameLabel, style = MaterialTheme.typography.body2)
                        TextField(
                            value = field1,
                            onValueChange = { viewModel.onNameChanged(it) },
                            label = { Text(viewModel.nameLabel) },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(text = viewModel.emailLabel, style = MaterialTheme.typography.body2)
                        TextField(
                            value = field2,
                            onValueChange = { viewModel.onEmailChanged(it) },
                            label = { Text(viewModel.emailLabel) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = viewModel.descriptionAgree, style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(horizontal = 30.dp), color = Color.Gray)
                    Button(onClick = {
                        viewModel.confirmButton.action()
                        navController.navigate("fourth")
                    },
                        shape = RoundedCornerShape(10.dp),
                        elevation = null,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        ),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                        enabled = button?.enable ?: false
                    ) {
                        button?.title?.let {
                            Text(it)
                        }

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
    SetupInterviewedView(
        navController = rememberNavController(),
        viewModel = SetupInterviewedViewModelImpl(
            rememberCoroutineScope()
        ).apply {
            setDateAndTime(LocalDate.parse("2025-04-22"), LocalTime.parse("18:00:00"))
        },
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview() {
    SetupInterviewedView(
        navController = rememberNavController(),
        viewModel = SetupInterviewedViewModelImpl(
            rememberCoroutineScope()
        ).apply {
            setDateAndTime(LocalDate.parse("2025-04-22"), LocalTime.parse("19:00:00"))
        },
    )
}