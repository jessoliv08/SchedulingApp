package com.example.schedulingapp.android.view.meeting

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schedulingapp.MeetingInfoViewModel
import com.example.schedulingapp.MeetingInfoViewModelImpl
import com.example.schedulingapp.android.MyApplicationTheme
import com.example.schedulingapp.android.getImageAssetName
import com.example.schedulingapp.android.view.components.IconTextView

@Composable
fun MeetingInfoView(
    navController: NavHostController,
    viewModel: MeetingInfoViewModel
) {
    val button by viewModel.cancelButton.state.collectAsState()

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
                    Text(text = viewModel.interviewInfo, style = MaterialTheme.typography.h1)
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth()
                    ) {
                        IconTextView(viewModel.timeInfo)
                        IconTextView(viewModel.callInfo)
                        IconTextView(viewModel.timeZone)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        viewModel.cancelButton.action()
                        navController.navigate("first")
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
    MeetingInfoView(
        navController = rememberNavController(),
        viewModel = MeetingInfoViewModelImpl(),
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview() {
    MeetingInfoView(
        navController = rememberNavController(),
        viewModel = MeetingInfoViewModelImpl(),
    )
}