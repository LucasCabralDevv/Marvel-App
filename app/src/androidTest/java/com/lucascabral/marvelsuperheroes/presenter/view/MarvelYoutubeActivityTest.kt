package com.lucascabral.marvelsuperheroes.presenter.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.lucascabral.marvelsuperheroes.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MarvelYoutubeActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MarvelYoutubeActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.marvelYoutubeActivity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isProgressBarDisplayed() {
        onView(withId(R.id.youtubeProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isRecyclerViewDisplayed() {
        onView(withId(R.id.youtubeRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_ProgressBar() {
        onView(withId(R.id.youtubeProgressBar)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_visibility_RecyclerView() {
        onView(withId(R.id.youtubeRecyclerView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}