package org.wikipedia.e2e.pages

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.hamcrest.Matchers.allOf
import org.wikipedia.R

class FeedSettingsPage : Page() {
    private val _contentTypesRecycler = withId(R.id.content_types_recycler)
    private val _feedContentTypeCheckbox = withId(R.id.feed_content_type_checkbox)

    override fun verify(): FeedSettingsPage {
        device.wait(Until.findObject(By.res("content_types_recycler")), 2000)
        device.wait(Until.findObject(By.res("feed_content_type_checkbox")), 2000)

        return this
    }

    fun assertThatAllCheckboxesChecked(): FeedSettingsPage {
        for (i in 0..6) {
            onView(allOf(_feedContentTypeCheckbox, isDescendantOfA(allOf(withParent(_contentTypesRecycler), withParentIndex(i)))))
                .check(matches(isChecked()))
        }
        onView(allOf(_contentTypesRecycler))
            .perform(RecyclerViewActions.scrollToLastPosition<RecyclerView.ViewHolder>())
        for (i in 0..6) {
            onView(allOf(_feedContentTypeCheckbox, isDescendantOfA(allOf(withParent(_contentTypesRecycler), withParentIndex(i)))))
                .check(matches(isChecked()))
        }

        return this
    }
}
