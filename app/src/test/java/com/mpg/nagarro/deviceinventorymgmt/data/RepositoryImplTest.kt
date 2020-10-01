package com.mpg.nagarro.deviceinventorymgmt.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.Result


class RepositoryImplTest : Repository {

    private var tasksServiceData: LinkedHashMap<String, DeviceEntity> = LinkedHashMap()
    private val observableTasks = MutableLiveData<List<DeviceEntity>>()

    private var empServiceData: LinkedHashMap<String, EmployeeEntity> = LinkedHashMap()
    private val observableEmps = MutableLiveData<List<EmployeeEntity>>()

    private var devInsServiceData: LinkedHashMap<String, DeviceInventory> = LinkedHashMap()
    private val observableDenIns = MutableLiveData<List<DeviceInventory>>()

    /**
     *  Get Result DeviceEntity List*/
    override suspend fun getDevices(): Result<List<DeviceEntity>> {
        return Result.Success(tasksServiceData.values.toList())
    }

    /**
     * To Fetch All Devices from Database*/
    override fun getDeviceList(): LiveData<List<DeviceEntity>> {
        return observableTasks
    }


    /**
     * To add Device information into Database*/
    override suspend fun addDevice(deviceEntity: DeviceEntity) {
        TODO("Not yet implemented")
    }

    fun addDevice(vararg deviceEntity: DeviceEntity) {
        for (task in deviceEntity) {
            tasksServiceData[task.deviceId.toString()] = task
        }
        observableTasks.value = tasksServiceData.values.toList()
    }

    /**
     * Select a device by id.
     *
     */
    override suspend fun getDeviceById(deviceId: Int): DeviceEntity? {
        TODO("Not yet implemented")
    }


    /**
     * To update Device information into Database*/
    override suspend fun updateDevice(deviceEntity: DeviceEntity) {
        TODO("Not yet implemented")
    }

    /**
     * Update a device.
     *
     * @param totalInventory Device totalInventory to be updated
     * @param deviceId Device id
     * @return the number of Devices updated. This should always be 1.
     */
    override suspend fun updateTotalInventory(totalInventory: Int, deviceId: Int): Int {
        TODO("Not yet implemented")
    }

    /**
     * To remove device record from Database*/
    override suspend fun deleteDevice(deviceId: Int): Int {
        TODO("Not yet implemented")
    }

    /**
     * To Fetch All Available Devices from Database*/
    override fun observeAvailableDevices(): LiveData<List<DeviceEntity>> {
        TODO("Not yet implemented")
    }

    /**
     * To update Device Current Available information into Database*/
    override suspend fun updateAvailableInventory(currentInventory: Int, deviceId: Int): Int {
        TODO("Not yet implemented")
    }


    /**
     * To add Employee information into Database*/
    override suspend fun addEmployee(employeeEntity: EmployeeEntity) {
        TODO("Not yet implemented")
    }

    fun addEmployee(vararg employeeEntities: EmployeeEntity) {
        for (emp in employeeEntities) {
            empServiceData[emp.empId.toString()] = emp
        }
        observableEmps.value = empServiceData.values.toList()
    }

    /**
     * Get a single Employee.
     *
     * @param empId the Employee id.
     * @return the Employee with empId.
     */
    override suspend fun getEmployeeById(empId: Int): EmployeeEntity? {
        TODO("Not yet implemented")
    }

    /**
     * To remove Employee record to Database*/
    override suspend fun deleteEmployee(empId: Int): Int {
        TODO("Not yet implemented")
    }

    /**
     * To Fetch All Employees from Database*/
    override fun getEmployeeList(): LiveData<List<EmployeeEntity>> {
        return observableEmps
    }

    /**
     * To add DeviceInventory information into Database*/
    override suspend fun addDeviceInventory(deviceInventory: DeviceInventory) {
        TODO("Not yet implemented")
    }

    fun addDevInventory(vararg deviceInventories: DeviceInventory) {
        for (devInv in deviceInventories) {
            devInsServiceData[devInv.recordId.toString()] = devInv
        }
        observableDenIns.value = devInsServiceData.values.toList()
    }

    /**
     * To remove DeviceInventory record to Database*/
    override suspend fun deleteDeviceInventory(recordId: Int): Int {
        TODO("Not yet implemented")
    }

    /**
     * To update DeviceInventory information into Database*/
    override suspend fun updateDeviceInventory(deviceInventory: DeviceInventory): Int {
        TODO("Not yet implemented")
    }

    /**
     * To remove DeviceInventory record to Database*/
    override suspend fun updateInventoryStatus(recordId: Int, status: Int): Int {
        TODO("Not yet implemented")
    }

    /**
     * To Fetch All DeviceInventory from Database*/
    override fun getDeviceInventoryList(): LiveData<Result<List<DeviceInventory>>> {
        TODO("Not yet implemented")
    }

    fun getDeviceInventories(): LiveData<List<DeviceInventory>> {
        return observableDenIns
    }

    /**
     * Get a DeviceInventory by id.
     *@param recordId for recordId
     * @return the number of DeviceInventory deleted. This should always be 1.
     */
    override suspend fun getDeviceInventoryById(recordId: Int): DeviceInventory? {
        TODO("Not yet implemented")
    }

    /**
     * Observes list of DeviceInventory.
     * @return all DeviceInventory.
     */
    override suspend fun getAllIssuedOrLostInventoryOfEmpId(empId: Int): List<DeviceInventory> {
        TODO("Not yet implemented")
    }
}