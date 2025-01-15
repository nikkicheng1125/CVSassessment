package com.example.cvsassesement.data.dto

data class GetPhotoResponse(
    val title: String,
    val link: String,
    val description: String,
    val modified: String,
    val generator: String,
    val items: List<Item>
)
