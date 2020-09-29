/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mpg.nagarro.deviceinventorymgmt.util

/**
 * Extension functions and Binding Adapters.
 */

import android.app.DatePickerDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.util.*


/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showSnackbar(snackbarText: String, timeLength: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, snackbarText, timeLength).run {
        show()
    }
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
//fun View.setupSnackbar(
//    lifecycleOwner: LifecycleOwner,
//    snackbarEvent: LiveData<Event<Int>>,
//    timeLength: Int
//) {
//
//    snackbarEvent.observe(lifecycleOwner, Observer { event ->
//        event.getContentIfNotHandled()?.let {
//            showSnackbar(context.getString(it), timeLength)
//        }
//    })
//}

fun View.showDatePicker() {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val dpd = DatePickerDialog(
        this.context, DatePickerDialog.OnDateSetListener
        { view, yearI, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            val monthOfYear = monthOfYear + 1
            when (this) {
                is Button -> this.text =
                    StringBuffer("Returned Date: $dayOfMonth/$monthOfYear/$yearI")
                is TextView -> this.text =
                    StringBuffer("Returned Date: $dayOfMonth/$monthOfYear/$yearI")
                else -> throw Exception("Text Property not supported ")

            }
        },
        year, month, day
    )

    dpd.datePicker.minDate = System.currentTimeMillis() + 86400000
    dpd.show()
}

fun View.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}