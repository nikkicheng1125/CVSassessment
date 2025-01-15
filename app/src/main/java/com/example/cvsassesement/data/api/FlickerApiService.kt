package com.example.cvsassesement.data.api

import com.example.cvsassesement.data.dto.GetPhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerApiService {

    @GET(Constants.ENDPOINT)
    suspend fun getPhotos(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("tags") tags: String
    ): GetPhotoResponse
}