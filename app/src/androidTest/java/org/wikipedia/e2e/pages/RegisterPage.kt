package org.wikipedia.e2e.pages

import android.content.res.Resources
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.os.ConfigurationCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import com.google.android.material.textfield.TextInputLayout
import junit.framework.Assert.assertNull
import org.hamcrest.Description
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.wikipedia.R
import org.wikipedia.views.PlainPasteEditText


class RegisterPage : Page() {
    private val _registerButton = withId(R.id.create_account_submit_button)
    private val _passwordField = withId(R.id.create_account_password_input)
    private val _usernameField = withId(R.id.create_account_username)
    private val _eyeIcon = withId(com.google.android.material.R.id.text_input_end_icon)

    private fun hasPasswordTransformation(isToggledVisible: Boolean): ViewAssertion {
        return ViewAssertion { view: View, _: NoMatchingViewException? ->
            assertTrue(view is TextInputLayout)
            val editText = (view as TextInputLayout).editText
            val transformationMethod = editText!!.transformationMethod
            if (isToggledVisible) {
                assertNull(transformationMethod)
            } else {
                assertEquals(PasswordTransformationMethod.getInstance(), transformationMethod)
            }
        }
    }

    private fun hasTextErrorColor(colorResId: Int): BoundedMatcher<View?, TextInputLayout> {
        return object : BoundedMatcher<View?, TextInputLayout>(TextInputLayout::class.java){

            override fun describeTo(description: Description) {
                var colorId = colorResId.toString()
                if (InstrumentationRegistry.getInstrumentation().targetContext != null) {
                    colorId = InstrumentationRegistry.getInstrumentation()
                        .targetContext.resources.getResourceName(colorResId)
                }
                description.appendText("has color with ID $colorId")
            }

            override fun matchesSafely(textView: TextInputLayout): Boolean {
                val textViewColor = textView.errorCurrentTextColors
                val expectedColor: Int = InstrumentationRegistry.
                getInstrumentation().targetContext.getColor(colorResId)

                return textViewColor == expectedColor
            }
        }
    }


    override fun verify(): RegisterPage {
        device.wait(Until.findObject(By.res("create_account_submit_button")), 2000)
        return this
    }

    fun clickRegisterButton(): RegisterPage {
        onView(_registerButton)
            .perform(click())
        return this
    }

    fun fillPassword(password: String): RegisterPage {
        onView(allOf(instanceOf(PlainPasteEditText::class.java), isDescendantOfA(_passwordField)))
            .perform(typeText(password))

        return this
    }

    fun fillUsername(username: String): RegisterPage {
        onView(allOf(instanceOf(PlainPasteEditText::class.java), isDescendantOfA(_usernameField)))
            .perform(typeText(username))

        return this
    }

    fun clickEyeIcon(): RegisterPage {
        onView(allOf(_eyeIcon, isDescendantOfA(_passwordField)))
            .perform(click())

        return this
    }

    fun assertThatPasswordVisible(isToggledVisible: Boolean): RegisterPage {
        onView(_passwordField)
            .check(hasPasswordTransformation(isToggledVisible))

        return this
    }

    fun assertThatPasswordHasRedError(): RegisterPage {
        onView(_passwordField)
            .check(matches(anyOf(hasTextErrorColor(R.color.red500),
                hasTextErrorColor(R.color.red700))))

        return this
    }

    fun assertThatRedErrorAppeared(): RegisterPage {
        val currentLocale = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0)

        val matchedText = if (currentLocale.toString() == "en_US")
            "The password must be at least 8 characters"
        else
            "Пароль должен состоять не менее чем из 8 символов."

        onView(withText(R.string.create_account_password_error))
            .check(matches(allOf(withText(matchedText),
                anyOf(hasTextColor(R.color.red500), hasTextColor(R.color.red700)))))

        return this
    }
}
