package com.mpg.nagarro.deviceinventorymgmt.data

import androidx.lifecycle.LiveData
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory


interface DeviceInventoryRepository {
    /**
     * To add DeviceInventory information into Database*/
    suspend fun addDeviceInventory(deviceInventory: DeviceInventory)

    /**
     * To remove DeviceInventory record to Database*/
    suspend fun deleteDeviceInventory(recordId: Int): Int

    /**
     * To update DeviceInventory information into Database*/
    suspend fun updateDeviceInventory(deviceInventory: DeviceInventory): Int

    /**
     * To remove DeviceInventory record to Database*/
    suspend fun updateInventoryStatus(recordId: Int, status: Int): Int

    /**
     * To Fetch All DeviceInventory from Database*/
    fun getDeviceInventoryList(): LiveData<List<DeviceInventory>>


}