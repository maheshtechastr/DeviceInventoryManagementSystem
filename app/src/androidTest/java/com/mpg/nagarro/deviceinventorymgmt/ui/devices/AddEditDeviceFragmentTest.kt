package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.mpg.nagarro.deviceinventorymgmt.R
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class AddEditDeviceFragmentTest {

    @Test
    fun onAddButtonClicked() {
        launchFragmentInContainer<AddEditDeviceFragment>(null, R.style.AppTheme)
//        onView(withId(R.id.btn_add_device))            // withId(R.id.my_view) is a ViewMatcher
//            .perform(click())               // click() is a ViewAction
//            .check(matches(isDisplayed()))  // matches(isDisplayed()) is a ViewAssertion
    }

}