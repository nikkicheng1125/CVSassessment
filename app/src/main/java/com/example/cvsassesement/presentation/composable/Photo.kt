package com.example.cvsassesement.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.cvsassesement.R
import com.example.cvsassesement.data.dto.Media
import com.example.cvsassesement.domain.model.Image


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Photo(image: Image, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)
            .clickable {
                onClick()
            }
            .testTag("Photo"),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box {
            GlideImage(
                model = image.media.m,
                contentDescription = image.tags,
                modifier = Modifier.fillMaxSize(),
                loading = placeholder {
                    CircularProgressIndicator(
                        Modifier
                            .align(Alignment.Center)
                            .size(20.dp))
                },
                failure = placeholder(R.drawable.ic_sentiment_very_dissatisfied)
            )
        }
    }
}

@Preview
@Composable
fun PhotoPreview() {
    Surface(color = Color.White) {
        Photo(
            image = Image(
                title = "IMG_0999",
                media = Media(
                    m = "https://live.staticflickr.com/65535/54255022494_0ba648ff48_m.jpg"
                ),
                published = "2025-01-08T19:43:32Z",
                author = "nobody@flickr.com (\"Mac Spud\")",
                tags = "london zoo porcupine"
            ),
            {}
        )
    }
}