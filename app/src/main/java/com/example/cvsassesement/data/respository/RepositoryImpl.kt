package com.example.cvsassesement.data.respository

import com.example.cvsassesement.data.api.FlickerApiService
import com.example.cvsassesement.data.converter.PhotoConverterImpl
import com.example.cvsassesement.domain.model.Image
import com.example.cvsassesement.domain.repository.IRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val flickerApiService: FlickerApiService) :
    IRepository {
    override suspend fun getPhotos(keyword: String): List<Image>? {
        return PhotoConverterImpl.convertToDomain(flickerApiService.getPhotos(tags = keyword))
    }
}