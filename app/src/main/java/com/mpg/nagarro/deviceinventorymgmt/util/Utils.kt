package com.mpg.nagarro.deviceinventorymgmt.util

import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceStatus

object Utils {
    fun enumToIntDeviceStatus(deviceStatus: DeviceStatus) =
        when (deviceStatus) {
            DeviceStatus.AVAILABLE -> 1
            DeviceStatus.ISSUED -> 2
            DeviceStatus.RETURNED -> 3
            DeviceStatus.LOST -> 4
        }

    fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    internal fun isNullOrEmpty(name: String?): Boolean {
        return (name == null || name.isEmpty())
    }
}