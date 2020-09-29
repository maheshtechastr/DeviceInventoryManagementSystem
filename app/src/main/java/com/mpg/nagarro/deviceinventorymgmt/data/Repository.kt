package com.mpg.nagarro.deviceinventorymgmt.data

import androidx.lifecycle.LiveData
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity

interface Repository : EmployeeRepository, DeviceInventoryRepository {
    /**
     * To add Device information into Database*/
    suspend fun addDevice(deviceEntity: DeviceEntity)

    /**
     * To Fetch All Devices from Database*/
    fun getDeviceList(): LiveData<List<DeviceEntity>>

    /**
     * To update Device information into Database*/
    suspend fun updateDevice(deviceEntity: DeviceEntity)

    /**
     * To remove device record from Database*/
    suspend fun deleteDevice(deviceId: Int): Int

    /**
     * To Fetch All Available Devices from Database*/
    fun observeAvailableDevices(): LiveData<List<DeviceEntity>>

    /**
     * To update Device Current Available information into Database*/
    suspend fun updateAvailableInventory(currentInventory: Int, deviceId: Int):Int
}