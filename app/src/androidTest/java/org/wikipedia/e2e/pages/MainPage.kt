package org.wikipedia.e2e.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.wikipedia.R

class MainPage : Page() {
    private val _moreButton = withId(R.id.nav_more_container)
    private val _settingButton = withId(R.id.main_drawer_settings_container)

    override fun verify(): MainPage {
        device.wait(Until.findObject(By.res("nav_more_container")), 2000)
        return this
    }

    fun clickMore(): MainPage {
        onView(_moreButton).perform(click())
        return this
    }

    fun clickSettings(): MainPage {
        onView(_settingButton).perform(click())
        return this
    }
}
