package com.mpg.nagarro.deviceinventorymgmt.data

import androidx.lifecycle.LiveData
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity

interface InventoryDataSource {
    /**
     * To add Device information into Database*/
    suspend fun addDevice(deviceEntity: DeviceEntity)

    /**
     * To Fetch All Devices from Database*/
    fun getDeviceList(): LiveData<List<DeviceEntity>>

    /**
     * To Fetch All Available Devices from Database*/
    fun observeAvailableDevices(): LiveData<List<DeviceEntity>>

    /**
     * To update Device Current Available information into Database*/
    suspend fun updateAvailableInventory(currentInventory: Int, deviceId: Int): Int

    /**
     * To Fetch All Employees from Database*/
    fun getEmployeeList(): LiveData<List<EmployeeEntity>>

    /**
     * To add Employee information into Database*/
    suspend fun addEmployee(employeeEntity: EmployeeEntity)

    /**
     * To remove Employee record to Database*/
    suspend fun deleteEmployee(empId: Int): Int


    /**
     * To update Device information into Database*/
    suspend fun updateDevice(deviceEntity: DeviceEntity): Int

    /**
     * To remove device record from Database*/
    suspend fun deleteDevice(deviceId: Int): Int

    /**
     * To Fetch All DeviceInventory from Database*/
    fun getDeviceInventoryList(): LiveData<List<DeviceInventory>>

    /**
     * To add DeviceInventory information into Database*/
    suspend fun addDeviceInventory(deviceInventory: DeviceInventory)

    /**
     * To remove DeviceInventory record to Database*/
    suspend fun deleteDeviceInventory(empId: Int): Int

    /**
     * To update DeviceInventory information into Database*/
    suspend fun updateDeviceInventory(deviceInventory: DeviceInventory)
}