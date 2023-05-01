package org.wikipedia.e2e.pages

import android.app.Instrumentation
import android.content.Intent
import android.content.res.Resources
import androidx.core.os.ConfigurationCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.wikipedia.R

class SettingsPage : Page() {
    private val _recyclerView = withId(R.id.recycler_view)

    override fun verify(): SettingsPage {
        device.wait(Until.findObject(By.res("recycler_view")), 2000)
        return this
    }

    fun clickExploreFeed(): SettingsPage {
        onView(_recyclerView)
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        return this
    }

    fun clickAbout(): SettingsPage {
        onView(_recyclerView)
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(16, click()))

        return this
    }

    fun clickPrivacyPolicyAndCheckBrowserIsOpened(): SettingsPage {
        val currentLocale = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0)

        Intents.init()
        val expectedIntent: Matcher<Intent> = if (currentLocale.toString() == "en_US")
            allOf(hasAction(Intent.ACTION_VIEW),
                hasData("https://foundation.wikimedia.org/wiki/Privacy_policy"))
        else
            allOf(hasAction(Intent.ACTION_VIEW),
                hasData("https://meta.m.wikimedia.org/wiki/Privacy_policy/ru"))
        intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))

        onView(_recyclerView)
            .perform(RecyclerViewActions.
            actionOnItemAtPosition<RecyclerView.ViewHolder>(18, click()))

        intended(expectedIntent)
        Intents.release()

        return this
    }

}
