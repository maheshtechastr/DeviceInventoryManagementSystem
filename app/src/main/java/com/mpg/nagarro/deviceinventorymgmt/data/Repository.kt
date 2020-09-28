package com.mpg.nagarro.deviceinventorymgmt.data

import androidx.lifecycle.LiveData
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity

interface Repository {
    /**
     * To add Device information into Database*/
    suspend fun addDevice(deviceEntity: DeviceEntity)

    /**
     * To Fetch All Devices from Database*/
    fun getDeviceList(): LiveData<List<DeviceEntity>>

    /**
     * To Fetch All Employees from Database*/
    fun getEmployeeList(): LiveData<List<EmployeeEntity>>

    /**
     * To Fetch All DeviceInventory from Database*/
    fun getDeviceInventoryList(): LiveData<List<DeviceInventory>>

    /**
     * To add DeviceInventory information into Database*/
    suspend fun addDeviceInventory(deviceInventory: DeviceInventory)

    /**
     * To add Employee information into Database*/
    suspend fun addEmployee(employeeEntity: EmployeeEntity)

    /**
     * To remove Employee record to Database*/
    suspend fun deleteEmployee(empId: Int):Int

//    /**
//     * To update Employee information into Database*/
//    fun updateEmployee(employeeEntity: EmployeeEntity)
//
//    /**
//     * To update Device information into Database*/
//    fun updateDevice(deviceEntity: DeviceEntity)

    /**
     * To remove device record from Database*/
    suspend fun deleteDevice(deviceId: Int): Int

    /**
     * To remove DeviceInventory record to Database*/
    suspend fun deleteDeviceInventory(empId: Int): Int

    /**
     * To update DeviceInventory information into Database*/
    suspend fun updateDeviceInventory(deviceInventory: DeviceInventory)

}