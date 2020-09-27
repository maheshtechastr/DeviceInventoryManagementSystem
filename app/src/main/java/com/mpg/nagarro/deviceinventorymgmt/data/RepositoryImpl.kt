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
    override fun deleteEmployee(employeeEntity: EmployeeEntity) {
        TODO("Not yet implemented")
    }

    /**
     * To update Employee information into Database*/
    override fun updateEmployee(employeeEntity: EmployeeEntity) {
        TODO("Not yet implemented")
    }

    /**
     * To update Device information into Database*/
    override fun updateDevice(deviceEntity: DeviceEntity) {
        TODO("Not yet implemented")
    }

    /**
     * To remove device record from Database
     * @param deviceEntity*/
    override fun deleteDevice(deviceEntity: DeviceEntity) {
        TODO("Not yet implemented")
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