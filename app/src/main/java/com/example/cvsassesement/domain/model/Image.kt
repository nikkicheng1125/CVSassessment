package com.example.cvsassesement.domain.model

import android.os.Parcelable
import com.example.cvsassesement.data.dto.Media
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val title: String,
    val media: Media,
    val published: String,
    val author: String,
    val tags: String
): Parcelable
