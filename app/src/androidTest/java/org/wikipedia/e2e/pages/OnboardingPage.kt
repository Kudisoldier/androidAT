package org.wikipedia.e2e.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.wikipedia.R

class OnboardingPage : Page() {
    private val _skipButton = withId(R.id.fragment_onboarding_skip_button)

    override fun verify(): OnboardingPage {
        device.wait(Until.findObject(By.res("fragment_onboarding_skip_button")), 2000)
        return this
    }

    fun clickSkip(): OnboardingPage {
        onView(_skipButton).perform(click())
        return this
    }
}
