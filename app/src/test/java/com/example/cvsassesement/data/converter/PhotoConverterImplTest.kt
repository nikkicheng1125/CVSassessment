package com.example.cvsassesement.data.converter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cvsassesement.data.dto.GetPhotoResponse
import com.example.cvsassesement.data.dto.Item
import com.example.cvsassesement.data.dto.Media
import com.example.cvsassesement.domain.model.Image
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PhotoConverterImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var classUnderTest: PhotoConverterImpl

    @Before
    fun setup() {
        classUnderTest = PhotoConverterImpl
    }

    @Test
    fun `Given GetPhotoResponse convert to a list of Image`() {
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

        val actual = classUnderTest.convertToDomain(photoResponse)

        assertEquals(expected, actual)
    }
}