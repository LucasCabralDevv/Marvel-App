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
class SplashScreenActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SplashScreenActivity::class.java)

    @Test
    fun test_isActivityInView_isViewsDisplayed_isViewsVisible() {
        onView(withId(R.id.splashScreen)).check(matches(isDisplayed()))

        onView(withId(R.id.marvelLogoImageView)).check(matches(isDisplayed()))
        onView(withId(R.id.ironManLottieView)).check(matches(isDisplayed()))

        onView(withId(R.id.marvelLogoImageView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.ironManLottieView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}