package org.wikipedia.e2e.tests

import org.wikipedia.e2e.pages.Page
import org.wikipedia.e2e.pages.MainPage
import org.wikipedia.e2e.pages.AboutPage
import org.wikipedia.e2e.pages.FeedSettingsPage
import org.wikipedia.e2e.pages.OnboardingPage
import org.wikipedia.e2e.pages.RegisterPage
import org.wikipedia.e2e.pages.SettingsPage
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.main.MainActivity

@RunWith(AndroidJUnit4::class)
class SimpleTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testFeedDefaultSettings() {
        Page.on<OnboardingPage>()
            .clickSkip()
            .on<MainPage>()
            .clickMore()
            .clickSettings()
            .on<SettingsPage>()
            .clickExploreFeed()
            .on<FeedSettingsPage>()
            .assertThatAllCheckboxesChecked()
    }

    @Test
    fun testAboutApp() {
        Page.on<OnboardingPage>()
            .clickSkip()
            .on<MainPage>()
            .clickMore()
            .clickSettings()
            .on<SettingsPage>()
            .clickAbout()
            .on<AboutPage>()
            .assertThatContribTranslatorsAndLicenseDisplayed()
    }

    @Test
    fun testBrowserOpened() {
        Page.on<OnboardingPage>()
            .clickSkip()
            .on<MainPage>()
            .clickMore()
            .clickSettings()
            .on<SettingsPage>()
            .clickPrivacyPolicyAndCheckBrowserIsOpened()
    }

    @Test
    fun testPasswordHidden() {
        val password = "passwd"

        Page.on<OnboardingPage>()
            .clickSkip()
            .on<MainPage>()
            .clickMore()
            .clickLogin()
            .on<RegisterPage>()
            .fillPassword(password)
            .clickEyeIcon()
            .assertThatPasswordVisible(true)
            .clickEyeIcon()
            .assertThatPasswordVisible(false)
    }

    @Test
    fun testPasswordValidation() {
        val username = "name"
        val password = "passwd"

        Page.on<OnboardingPage>()
            .clickSkip()
            .on<MainPage>()
            .clickMore()
            .clickLogin()
            .on<RegisterPage>()
            .fillUsername(username)
            .fillPassword(password)
            .clickRegisterButton()
            .assertThatPasswordHasRedError()
            .assertThatRedErrorAppeared()
    }
}
