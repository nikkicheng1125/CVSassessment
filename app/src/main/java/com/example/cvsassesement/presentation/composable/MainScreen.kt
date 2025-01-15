package com.example.cvsassesement.presentation.composable

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cvsassesement.presentation.viewmodel.PhotoViewModel

@Composable
fun MainScreen(viewModel: PhotoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "photo_list") {
        composable("photo_list") {
            PhotoList( navController,viewModel )
        }

        composable("photo_detail") {
            ImageDetailScreen(viewModel)
        }
    }
}