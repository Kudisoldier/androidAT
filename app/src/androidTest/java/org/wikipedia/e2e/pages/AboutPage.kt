package org.wikipedia.e2e.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.wikipedia.R

class AboutPage : Page() {
    private val _aboutContributors = withId(R.id.about_contributors)
    private val _aboutTranslators = withId(R.id.about_translators)
    private val _aboutLicense = withId(R.id.about_app_license)

    override fun verify(): AboutPage {
        device.wait(Until.findObject(By.res("about_contributors")), 2000)
        return this
    }

    fun assertThatContribTranslatorsAndLicenseDisplayed(): AboutPage {
        onView(_aboutContributors).check(matches(isDisplayed()))
        onView(_aboutTranslators).check(matches(isDisplayed()))
        onView(_aboutLicense).check(matches(isDisplayed()))

        return this
    }
}
