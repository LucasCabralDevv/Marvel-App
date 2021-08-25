package com.lucascabral.marvelsuperheroes.presenter.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.lucascabral.marvelsuperheroes.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AllCharactersActivityTest {

    @Test
    fun test_isActivityInView() {
        val activityScenario = ActivityScenario.launch(AllCharactersFragment::class.java)
        onView(withId(R.id.allCharactersFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isRecyclerViewDisplayed() {
        val activityScenario = ActivityScenario.launch(AllCharactersActivity::class.java)
        onView(withId(R.id.charactersRecyclerView)).check(matches(isDisplayed()))
    }
}