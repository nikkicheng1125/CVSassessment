package com.example.cvsassesement.domain.repository

import com.example.cvsassesement.data.dto.Item
import com.example.cvsassesement.domain.model.Image

interface IRepository {
    suspend fun getPhotos(keyword:String): List<Image>?
}