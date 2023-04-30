package org.wikipedia.e2e.pages

import androidx.test.espresso.Espresso
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

open class Page {
    val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    companion object {
        inline fun <reified T : Page> on(): T {
            return Page().on()
        }
    }

    inline fun <reified T : Page> on(): T {
        val page = T::class.constructors.first().call()
        page.verify()
        return page
    }

    open fun verify(): Page {
        // Each subpage should have its default assurances here
        return this
    }

    fun back(): Page {
        Espresso.pressBack()
        return this
    }
}
