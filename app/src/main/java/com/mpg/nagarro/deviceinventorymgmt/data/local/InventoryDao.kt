package com.mpg.nagarro.deviceinventorymgmt.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

//
//    /**
//     * Observes a single Device.
//     *
//     * @param deviceId the Device id.
//     * @return the Device with deviceId.
//     */
//    @Query("SELECT * FROM DeviceEntity WHERE deviceId = :deviceId")
//    fun observeTaskById(deviceId: Int): LiveData<DeviceEntity>
//
//    /**
//     * Select all Devices from the Device table.
//     *
//     * @return all devices.
//     */
//    @Query("SELECT * FROM DeviceEntity")
//    suspend fun getTasks(): List<DeviceEntity>
//
//    /**
//     * Select a device by id.
//     *
//     * @param deviceId the device id.
//     * @return the Device with deviceId.
//     */
//    @Query("SELECT * FROM DeviceEntity WHERE deviceId = :deviceId")
//    suspend fun getDeviceById(deviceId: String): DeviceEntity?
//
//
//
//    /**
//     * Update a device.
//     *
//     * @param task task to be updated
//     * @return the number of tasks updated. This should always be 1.
//     */
//    @Update
//    suspend fun updateTask(deviceEntity: DeviceEntity): Int

//    /**
//     * Update the complete status of a task
//     *
//     * @param taskId    id of the task
//     * @param completed status to be updated
//     */
//    @Query("UPDATE tasks SET completed = :completed WHERE entryid = :taskId")
//    suspend fun updateCompleted(taskId: String, completed: Boolean)
//
//    /**
//     * Delete a task by id.
//     *
//     * @return the number of tasks deleted. This should always be 1.
//     */
//    @Query("DELETE FROM Tasks WHERE entryid = :taskId")
//    suspend fun deleteTaskById(taskId: String): Int
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