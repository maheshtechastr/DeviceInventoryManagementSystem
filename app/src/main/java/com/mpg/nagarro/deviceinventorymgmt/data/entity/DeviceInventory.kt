package com.mpg.nagarro.deviceinventorymgmt.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class DeviceInventory(
    val status: Int,
    val issueDate: Date,
    val returnDate: Date,
    val empId: Int,
    val deviceId: Int,
    val empName: String,
    val devName: String,
    @PrimaryKey(autoGenerate = true) val recordId: Int
) {
    constructor(
        status: Int,
        issueDate: Date,
        returnDate: Date,
        empId: Int,
        deviceId: Int,
        empName: String,
        devName: String,
    ) : this(status, issueDate, returnDate, empId, deviceId, empName, devName, 0)
}