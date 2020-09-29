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
     * Select a device by id.
     *
     */
    suspend fun getDeviceById(deviceId: Int): DeviceEntity?

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
     * Update a device.
     *
     * @param totalInventory Device totalInventory to be updated
     * @param deviceId Device id
     * @return the number of Devices updated. This should always be 1.
     */
    suspend fun updateTotalInventory(totalInventory: Int, deviceId: Int): Int

    /**
     * To Fetch All Employees from Database*/
    fun getEmployeeList(): LiveData<List<EmployeeEntity>>

    /**
     * To add Employee information into Database*/
    suspend fun addEmployee(employeeEntity: EmployeeEntity)

    /**
     * Get a single Employee.
     *
     * @param empId the Employee id.
     * @return the Employee with empId.
     */
    suspend fun getEmployeeById(empId: Int): EmployeeEntity?

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
    suspend fun deleteDeviceInventory(recordId: Int): Int

    /**
     * To update DeviceInventory information into Database*/
    suspend fun updateDeviceInventory(deviceInventory: DeviceInventory): Int

    /**
     * To remove DeviceInventory record to Database*/
    suspend fun updateInventoryStatus(recordId: Int, status: Int): Int

    /**
     * Get a DeviceInventory by id.
     *@param recordId for recordId
     * @return the number of DeviceInventory deleted. This should always be 1.
     */
    suspend fun getDeviceInventoryById(recordId: Int): DeviceInventory?

    /**
     * Observes list of DeviceInventory.
     * @return all DeviceInventory.
     */
    suspend fun getAllIssuedOrLostInventoryOfEmpId(empId: Int): List<DeviceInventory>
}