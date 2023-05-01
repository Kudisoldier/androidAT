package org.wikipedia.e2e.pages

import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import com.google.android.material.textfield.TextInputLayout
import junit.framework.Assert.assertNull
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.wikipedia.R
import org.wikipedia.views.PlainPasteEditText


class RegisterPage : Page() {
    private val _registerButton = withId(R.id.create_account_submit_button)
    private val _passwordField = withId(R.id.create_account_password_input)
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

}
