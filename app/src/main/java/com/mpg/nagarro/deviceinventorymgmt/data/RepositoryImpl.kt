package com.mpg.nagarro.deviceinventorymgmt.data

import androidx.lifecycle.LiveData
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val inventoryDataSource: InventoryDataSource) :
    Repository {
    /**
     * To add Device information into Database
     * @param deviceEntity*/
    override suspend fun addDevice(deviceEntity: DeviceEntity) {
        inventoryDataSource.addDevice(deviceEntity)
    }

    /**
     * To Fetch All Devices from Database*/
    override fun getDeviceList(): LiveData<List<DeviceEntity>> {
        return inventoryDataSource.getDeviceList()
    }

    /**
     * To update Device information into Database*/
    override suspend fun updateDevice(deviceEntity: DeviceEntity) {
        inventoryDataSource.updateDevice(deviceEntity)
    }

    /**
     * To remove device record from Database
     * @param deviceId deviceId*/
    override suspend fun deleteDevice(deviceId: Int): Int {
        return inventoryDataSource.deleteDevice(deviceId)
    }

    /**
     * To Fetch All Available Devices from Database*/
    override fun observeAvailableDevices(): LiveData<List<DeviceEntity>> {
        return inventoryDataSource.observeAvailableDevices()
    }

    /**
     * To update Device Current Available information into Database*/
    override suspend fun updateAvailableInventory(currentInventory: Int, deviceId: Int): Int {
        return inventoryDataSource.updateAvailableInventory(currentInventory, deviceId)
    }

    /**
     * To Fetch All Employees from Database*/
    override fun getEmployeeList(): LiveData<List<EmployeeEntity>> {
        return inventoryDataSource.getEmployeeList()
    }

    /**
     * To add Employee information into Database
     * @param employeeEntity*/
    override suspend fun addEmployee(employeeEntity: EmployeeEntity) {
        inventoryDataSource.addEmployee(employeeEntity)
    }

    /**
     * To remove Employee record to Database*/
    override suspend fun deleteEmployee(empId: Int): Int {
        return inventoryDataSource.deleteEmployee(empId)
    }

    /**
     * To remove DeviceInventory record to Database*/
    override suspend fun deleteDeviceInventory(empId: Int): Int {
        return inventoryDataSource.deleteDeviceInventory(empId)
    }

    /**
     * To update DeviceInventory information into Database*/
    override suspend fun updateDeviceInventory(deviceInventory: DeviceInventory) {
        inventoryDataSource.updateDeviceInventory(deviceInventory)
    }

    /**
     * To add DeviceInventory information into Database
     * @param deviceInventory*/
    override suspend fun addDeviceInventory(deviceInventory: DeviceInventory) {
        inventoryDataSource.addDeviceInventory(deviceInventory)
    }

    /**
     * To Fetch All DeviceInventories from Database*/
    override fun getDeviceInventoryList(): LiveData<List<DeviceInventory>> {
        return inventoryDataSource.getDeviceInventoryList()
    }
}