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
     * Select a device by id.
     *
     */
    override suspend fun getDeviceById(deviceId: Int): DeviceEntity? {
        return dao.getDeviceById(deviceId)
    }

    /**
     * To Fetch All Devices from Database*/
    override fun getDeviceList(): LiveData<List<DeviceEntity>> {
        return dao.observeDevices()
    }

    /**
     * To Fetch All Available Devices from Database*/
    override fun observeAvailableDevices(): LiveData<List<DeviceEntity>> {
        return dao.observeAvailableDevices()
    }

    /**
     * To update Device Current Available information into Database*/
    override suspend fun updateAvailableInventory(currentInventory: Int, deviceId: Int): Int {
        return dao.updateAvailableInventory(currentInventory, deviceId)
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
     * Get a single Employee.
     *
     * @param empId the Employee id.
     * @return the Employee with empId.
     */
    override suspend fun getEmployeeById(empId: Int): EmployeeEntity? {
        return dao.getEmployeeById(empId)
    }

    /**
     * To remove Employee record to Database*/
    override suspend fun deleteEmployee(empId: Int): Int {
        return dao.deleteEmployeeById(empId)
    }

    /**
     * To update Device information into Database*/
    override suspend fun updateDevice(deviceEntity: DeviceEntity): Int {
        return dao.updateDevice(deviceEntity)
    }

    /**
     * To remove device record from Database*/
    override suspend fun deleteDevice(deviceId: Int): Int {
        return dao.deleteDeviceById(deviceId)
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

    /**
     * To remove DeviceInventory record to Database*/
    override suspend fun deleteDeviceInventory(recordId: Int): Int {
        return dao.deleteDeviceInventoryById(recordId)
    }

    /**
     * To update DeviceInventory information into Database*/
    override suspend fun updateDeviceInventory(deviceInventory: DeviceInventory): Int {
        return dao.updateInventory(deviceInventory)
    }

    /**
     * To remove DeviceInventory record to Database*/
    override suspend fun updateInventoryStatus(recordId: Int, status: Int): Int {
        return dao.updateInventoryStatus(recordId, status)
    }

    /**
     * Get a DeviceInventory by id.
     *@param recordId for recordId
     * @return the number of DeviceInventory deleted. This should always be 1.
     */
    override suspend fun getDeviceInventoryById(recordId: Int): DeviceInventory? {
        return dao.getDeviceInventoryById(recordId)
    }

    /**
     * Observes list of DeviceInventory.
     * @return all DeviceInventory.
     */
    override suspend fun getAllIssuedOrLostInventoryOfEmpId(empId: Int): List<DeviceInventory> {
        return dao.getAllIssuedOrLostInventoryOfEmpId(empId)
    }


}