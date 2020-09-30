package com.mpg.nagarro.deviceinventorymgmt.util

import timber.log.Timber


/**
 * Method for sleeping for a fixed about of time to emulate slower work
 */

fun sleep() {
    try {
        Thread.sleep(DELAY_TIME_MILLIS, 0)
    } catch (e: InterruptedException) {
        Timber.e(e.message)
    }

}