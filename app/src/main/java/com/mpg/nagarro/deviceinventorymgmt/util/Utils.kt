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

    fun intDeviceStatusToEnum(status: Int) =
        when (status) {
            1 -> DeviceStatus.AVAILABLE
            // 2-> DeviceStatus.ISSUED
            3 -> DeviceStatus.RETURNED
            4 -> DeviceStatus.LOST
            else -> DeviceStatus.ISSUED
        }

    fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    internal fun isNullOrEmpty(name: String?): Boolean {
        return (name == null || name.isEmpty())
    }
}