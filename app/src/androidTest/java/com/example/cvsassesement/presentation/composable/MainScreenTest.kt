package com.example.cvsassesement.presentation.composable

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cvsassesement.presentation.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun searchBarIsVisible() {
        composeTestRule.onNode(hasSetTextAction()).assertExists()
        composeTestRule.onNode(hasSetTextAction()).assertIsEnabled()
    }

    @Test
    fun photoGridInitiallyEmpty(){
        composeTestRule.onAllNodesWithTag("PhotoGrid").assertCountEquals(0)
    }
}