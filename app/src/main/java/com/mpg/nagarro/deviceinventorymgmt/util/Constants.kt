package com.mpg.nagarro.deviceinventorymgmt.util


// Notification Channel constants

// Name of Notification Channel for verbose notifications of background work
@JvmField
val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
    "Verbose WorkManager Notifications"
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
    "Shows notifications whenever work starts"

@JvmField
val NOTIFICATION_TITLE: CharSequence = "WorkRequest Starting"
const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
const val NOTIFICATION_ID = 1

const val OUTPUT_PATH = "Backup"
const val DELAY_TIME_MILLIS: Long = 300
