package com.mpg.nagarro.deviceinventorymgmt.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity

@Dao
interface InventoryDao {
    /**
     * Insert a device in the database. If the device already exists, replace it.
     *
     * @param deviceEntity the device to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDevice(deviceEntity: DeviceEntity)

    /**
     * Observes list of Devices.
     *
     * @return all Devices.
     */
    @Query("SELECT * FROM DeviceEntity")
    fun observeDevices(): LiveData<List<DeviceEntity>>

    /**
     * Insert a device in the database. If the device already exists, replace it.
     *
     * @param employeeEntity the device to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEmployee(employeeEntity: EmployeeEntity)

    /**
     * Observes list of Employees.
     *
     * @return all Employees.
     */
    @Query("SELECT * FROM EmployeeEntity")
    fun observeEmployees(): LiveData<List<EmployeeEntity>>

    /**
     * Insert a deviceInventory in the database. If the deviceInventory already exists, replace it.
     *
     * @param deviceInventory the device to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDeviceInventory(deviceInventory: DeviceInventory)

    /**
     * Observes list of DeviceInventory.
     *
     * @return all DeviceInventory.
     */
    @Query("SELECT * FROM DeviceInventory")
    fun observeDeviceInventories(): LiveData<List<DeviceInventory>>

    /**
     * Delete a Device by id.
     *
     * @return the number of devices deleted. This should always be 1.
     */
    @Query("DELETE FROM DeviceEntity WHERE deviceId = :deviceId")
    suspend fun deleteDeviceById(deviceId: Int): Int

    /**
     * Delete a Employee by id.
     *
     * @return the number of Employees deleted. This should always be 1.
     */
    @Query("DELETE FROM EmployeeEntity WHERE empId = :empId")
    suspend fun deleteEmployeeById(empId: Int): Int


    /**
     * Observes a single Device.
     *
     * @param deviceId the Device id.
     * @return the Device with deviceId.
     */
    @Query("SELECT * FROM DeviceEntity WHERE deviceId = :deviceId")
    fun observeDeviceById(deviceId: Int): LiveData<DeviceEntity>

    /**
     * Observes a single Device.
     *
     * @param empId the Employee id.
     * @return the Employee with empId.
     */
    @Query("SELECT * FROM EmployeeEntity WHERE empId = :empId")
    fun observeEmployeeById(empId: Int): LiveData<EmployeeEntity>

    /**
     * Select a device by id.
     *
     * @param deviceId the device id.
     * @return the Device with deviceId.
     */
    @Query("SELECT * FROM DeviceEntity WHERE deviceId = :deviceId")
    suspend fun getDeviceById(deviceId: Int): DeviceEntity?


    /**
     * Update the status of a DeviceInventory
     *
     * @param empId    id of the Employee
     * @param status status to be updated
     */
    @Query("UPDATE DeviceInventory SET status = :status WHERE empId = :empId")
    suspend fun updateInventoryStatus(empId: Int, status: Int)

    /**
     * Update the status of a DeviceInventory
     *
     * @param deviceInventory    id of the DeviceInventory
     */
    @Update
    suspend fun updateInventory(deviceInventory: DeviceInventory)

    /**
     * Delete a DeviceInventory by id.
     *
     * @return the number of DeviceInventory deleted. This should always be 1.
     */
    @Query("DELETE FROM DeviceInventory WHERE empId = :empId")
    suspend fun deleteDeviceInventoryById(empId: Int): Int

//    /**
//     * Select all Devices from the Device table.
//     *
//     * @return all devices.
//     */
//    @Query("SELECT * FROM DeviceEntity")
//    suspend fun getTasks(): List<DeviceEntity>
//

//    /**
//     * Update a device.
//     *
//     * @param deviceEntity Device to be updated
//     * @return the number of Devices updated. This should always be 1.
//     */
//    @Update
//    suspend fun updateDevice(deviceEntity: DeviceEntity): Int


//
//    /**
//     * Delete all tasks.
//     */
//    @Query("DELETE FROM Tasks")
//    suspend fun deleteTasks()
//
//    /**
//     * Delete all completed tasks from the table.
//     *
//     * @return the number of tasks deleted.
//     */
//    @Query("DELETE FROM Tasks WHERE completed = 1")
//    suspend fun deleteCompletedTasks(): Int
}