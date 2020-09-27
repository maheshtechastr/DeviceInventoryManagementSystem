package com.mpg.nagarro.deviceinventorymgmt.data.local

import androidx.lifecycle.LiveData
import com.mpg.nagarro.deviceinventorymgmt.data.InventoryDataSource
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import javax.inject.Inject

class LocalInventoryRepository @Inject constructor(
    private val dao: InventoryDao,
    //private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : InventoryDataSource {
    /**
     * To add Device information into Database*/
    override suspend fun addDevice(deviceEntity: DeviceEntity) {
        dao.addDevice(deviceEntity)
    }

    /**
     * To Fetch All Devices from Database*/
    override fun getDeviceList(): LiveData<List<DeviceEntity>> {
        return dao.observeDevices()
    }

    /**
     * To Fetch All Employees from Database*/
    override fun getEmployeeList(): LiveData<List<EmployeeEntity>> {
        return dao.observeEmployees()
    }

    /**
     * To add Employee information into Database*/
    override suspend fun addEmployee(employeeEntity: EmployeeEntity) {
        dao.addEmployee(employeeEntity)
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
     * To remove device record from Database*/
    override fun deleteDevice(deviceEntity: DeviceEntity) {
        TODO("Not yet implemented")
    }

    /**
     * To Fetch All DeviceInventory from Database*/
    override fun getDeviceInventoryList(): LiveData<List<DeviceInventory>> {
        return dao.observeDeviceInventories()
    }

    /**
     * To add DeviceInventory information into Database*/
    override suspend fun addDeviceInventory(deviceInventory: DeviceInventory) {
        return dao.addDeviceInventory(deviceInventory)
    }


}