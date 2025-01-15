package com.example.cvsassesement.presentation.composable


import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cvsassesement.domain.model.PhotoState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.testTag
import com.example.cvsassesement.presentation.viewmodel.PhotoViewModel

@Composable
fun PhotoList(
    navController: NavController,
    viewModel: PhotoViewModel
) {
    var searchQuery by remember { mutableStateOf("") }
    val uiState = viewModel.searchResult.observeAsState(initial = PhotoState.Loading)

    Scaffold(topBar = {
        TextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                viewModel.searchPhotos(searchQuery)
            },
            label = { Text("Search Photos") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("SearchBox"),
            singleLine = true
        )
    }
    ) { paddingValues ->
        when (val state = uiState.value) {
            is PhotoState.OnSuccess -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize().testTag("PhotoGrid"),
                    contentPadding = paddingValues
                ) {
                    items(state.images.size) { index ->
                        val image = state.images[index]
                        android.util.Log.d("MAIN_TEST_State", image.toString())
                        Photo(image) {
                            viewModel.selectPhoto(image)
                            navController.navigate("photo_detail") }
                    }
                }
            }

            is PhotoState.OnError -> {
                ErrorMessage(state.message)

            }

            is PhotoState.OnFailure -> {
                ErrorMessage(state.message)
            }

            is PhotoState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Display Photos Here",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}


@Composable
private fun ErrorMessage(message: String) {
    Text(
        text = message,
        color = Color.Black
    )
}

