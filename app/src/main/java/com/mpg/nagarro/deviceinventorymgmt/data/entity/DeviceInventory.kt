package com.mpg.nagarro.deviceinventorymgmt.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class DeviceInventory(
    val status: Int,
    val issueDate: Date,
    val returnDate: Date,
    @PrimaryKey val empId: Int,
    val deviceId: Int,
    val empName: String,
    val devName: String,
)