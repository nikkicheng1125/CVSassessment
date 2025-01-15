package com.example.cvsassesement.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cvsassesement.data.dto.Media
import com.example.cvsassesement.domain.model.Image
import com.example.cvsassesement.domain.model.PhotoState
import com.example.cvsassesement.domain.repository.IRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PhotoViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: IRepository = mockk()
    private lateinit var classUnderTest: PhotoViewModel

    @Before
    fun setup() {
        classUnderTest = PhotoViewModel(repository)
    }

    @After
    fun reset() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load photos should post Loading state when photos are fetching`(): Unit =
        runTest {
            val keyword = ""
            val photoList = listOf(
                Image(
                    title = "",
                    media = Media(m = ""),
                    published = "",
                    author = "",
                    tags = ""
                )
            )
            coEvery { repository.getPhotos(keyword) } returns photoList

            classUnderTest.searchPhotos(keyword)

            val expected = classUnderTest.searchResult.value

            assertEquals(
                PhotoState.Loading,
                expected
            )
        }

    @Test
    fun `load photos should post Success state when photos are fetched successfully`(): Unit =
        runTest {
            val keyword = ""
            val photoList = listOf(
                Image(
                    title = "",
                    media = Media(m = ""),
                    published = "",
                    author = "",
                    tags = ""
                )
            )
            coEvery { repository.getPhotos(keyword) } returns photoList

            classUnderTest.searchResult.observeForever { }
            classUnderTest.searchPhotos(keyword)

            coVerify(exactly = 1) { repository.getPhotos(keyword) }

            val actual = classUnderTest.searchResult.value

            assertEquals(
                PhotoState.OnSuccess(photoList),
                actual
            )
        }

    @Test
    fun `load photos should post Error state when fetched photos are empty`(): Unit =
        runTest {
            val keyword = ""
            val message = "Empty Search Result"
            coEvery { repository.getPhotos(keyword) } returns emptyList()

            classUnderTest.searchResult.observeForever { }
            classUnderTest.searchPhotos(keyword)

            coVerify(exactly = 1) { repository.getPhotos(keyword) }

            val actual = classUnderTest.searchResult.value

            assertEquals(
                PhotoState.OnError(message),
                actual
            )
        }

    @Test
    fun `load photos should post Failure state when photos are fetched unsuccessfully`(): Unit =
        runTest {
            val keyword = ""
            val exception = Exception()
            coEvery { repository.getPhotos(keyword) } throws exception

            classUnderTest.searchResult.observeForever { }
            classUnderTest.searchPhotos(keyword)

            coVerify(exactly = 1) { repository.getPhotos(keyword) }

            val actual = classUnderTest.searchResult.value

            assertEquals(
                PhotoState.OnFailure(exception.message.toString()),
                actual
            )
        }
}