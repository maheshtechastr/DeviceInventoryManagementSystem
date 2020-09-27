package com.mpg.nagarro.deviceinventorymgmt.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DeviceEntity(
    val name: String,
    val totalDeviceCount: Int = 1,
    @PrimaryKey(autoGenerate = true) val deviceId: Int,
) {
    constructor(
        name: String,
        totalDeviceCount: Int = 1,
    ) : this(name, totalDeviceCount, 0)

}