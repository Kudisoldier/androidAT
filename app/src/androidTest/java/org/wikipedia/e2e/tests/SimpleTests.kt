package org.wikipedia.e2e.tests

import org.wikipedia.e2e.pages.Page
import org.wikipedia.e2e.pages.MainPage
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.e2e.pages.AboutPage
import org.wikipedia.e2e.pages.FeedSettingsPage
import org.wikipedia.e2e.pages.OnboardingPage
import org.wikipedia.e2e.pages.SettingsPage
import org.wikipedia.main.MainActivity

@RunWith(AndroidJUnit4::class)
class SimpleTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

//    @Test
//    fun testFeedDefaultSettings() {
//        Page.on<OnboardingPage>()
//            .clickSkip()
//            .on<MainPage>()
//            .clickMore()
//            .clickSettings()
//            .on<SettingsPage>()
//            .clickExploreFeed()
//            .on<FeedSettingsPage>()
//            .assertThatAllCheckboxesChecked()
//    }

//    @Test
//    fun testAboutApp() {
//        Page.on<OnboardingPage>()
//            .clickSkip()
//            .on<MainPage>()
//            .clickMore()
//            .clickSettings()
//            .on<SettingsPage>()
//            .clickAbout()
//            .on<AboutPage>()
//            .assertThatContribTranslatorsAndLicenseDisplayed()
//    }


}