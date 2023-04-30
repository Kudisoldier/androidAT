package org.wikipedia.e2e.pages

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToLastPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.hamcrest.Matchers.allOf
import org.wikipedia.R

class SettingsPage : Page() {
    private val _recyclerView = withId(R.id.recycler_view)
    //private val _linearLayoutFeedSettings = allOf(withParent(_recyclerView), withParentIndex(2))

    override fun verify(): SettingsPage {
        device.wait(Until.findObject(By.res("recycler_view")), 2000)
        return this
    }

    fun clickExploreFeed(): SettingsPage {
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.
            actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        return this
    }

    fun clickAbout(): SettingsPage {
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.
            actionOnItemAtPosition<RecyclerView.ViewHolder>(16, click()))

        return this
    }

}
