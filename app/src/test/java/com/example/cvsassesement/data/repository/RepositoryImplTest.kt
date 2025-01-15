package com.example.cvsassesement.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cvsassesement.data.api.FlickerApiService
import com.example.cvsassesement.data.dto.GetPhotoResponse
import com.example.cvsassesement.data.dto.Item
import com.example.cvsassesement.data.dto.Media
import com.example.cvsassesement.data.respository.RepositoryImpl
import com.example.cvsassesement.domain.model.Image
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val apiService = mockk<FlickerApiService>(relaxed = true)
    private lateinit var classUnderTest: RepositoryImpl

    @Before
    fun setup() {
        classUnderTest = RepositoryImpl(apiService)
    }

    @Test
    fun `Given apiService returns photo data`() = runTest {
        val media = Media(m = "")

        val item = Item(
            title = "",
            link = "",
            media = media,
            date_taken = "",
            description = "",
            published = "",
            author = "",
            author_id = "",
            tags = ""
        )

        val photoResponse = GetPhotoResponse(
            title = "",
            link = "",
            description = "",
            modified = "",
            generator = "",
            items = listOf(item)
        )

        val expected = listOf(
            Image(
                title = "",
                media = Media(m = ""),
                published = "",
                author = "",
                tags = ""
            )
        )

        val keyword = ""

        coEvery { apiService.getPhotos(tags = keyword) } returns photoResponse

        val result = classUnderTest.getPhotos(keyword)

        assertEquals(expected, result)
    }
}