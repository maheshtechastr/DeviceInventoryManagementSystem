package com.mpg.nagarro.deviceinventorymgmt.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DeviceEntity(
    val name: String,
    val totalInventory: Int,
    val currentAvailableInventory: Int,
    @PrimaryKey(autoGenerate = true) val deviceId: Int,
) {
    constructor(
        name: String,
        totalInventory: Int,
        currentAvailableInventory: Int,
    ) : this(name, totalInventory, currentAvailableInventory, 0)

}