package org.wikipedia.e2e.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.hamcrest.Matchers.allOf
import org.wikipedia.R

class SettingsPage : Page() {
    private val _recyclerView = withId(R.id.recycler_view)
    private val _linearLayoutFeedSettings = allOf(withParent(_recyclerView), withParentIndex(2))

    override fun verify(): SettingsPage {
        device.wait(Until.findObject(By.res("recycler_view")), 2000)
        return this
    }

    fun clickExploreFeed(): SettingsPage {
        onView(_linearLayoutFeedSettings).perform(click())
        return this
    }


}
