package com.example.recorderjetpackcompose.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.recorderjetpackcompose.viewModels.RecordingsViewModel

@ExperimentalMaterial3Api
@Composable
fun RecordingListScreen(
    navController: NavController,
    viewModel: RecordingsViewModel,
) {
    PlayerBottomSheet(
        viewModel = viewModel,
        navController = navController
    )
}