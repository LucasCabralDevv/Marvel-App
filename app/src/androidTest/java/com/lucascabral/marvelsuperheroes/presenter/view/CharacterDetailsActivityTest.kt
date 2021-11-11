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
class CharacterDetailsActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(CharacterDetailsActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.detailsCharacterActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_views() {
        onView(withId(R.id.detailsCharacterThumbImageView)).check(matches(isDisplayed()))
        onView(withId(R.id.detailsCharacterDescriptionTextView)).check(matches(isDisplayed()))
    }
}
