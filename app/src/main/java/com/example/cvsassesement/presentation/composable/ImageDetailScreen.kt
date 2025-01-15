package com.example.cvsassesement.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.cvsassesement.presentation.viewmodel.PhotoViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageDetailScreen(viewModel: PhotoViewModel) {
    val image = viewModel.selectedImage.observeAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("ImageDetailScreen"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        image?.let {
            GlideImage(
                model = image.media.m,
                contentDescription = image.tags,
                modifier = Modifier.size(250.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Title: " + image.title,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Description: " + image.tags.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            extractName(image.author)?.let {
                Text(
                    text = "Author: $it",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            Text(
                text = "Taken on: " + extractDate(image.published),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

    }
}

private fun extractName(author: String): String? {
    val regex = "\"(.*?)\"".toRegex()
    val matchResult = regex.find(author)
    return matchResult?.groups?.get(1)?.value
}

private fun extractDate(published: String): String {
    return published.substringBefore("T")
}