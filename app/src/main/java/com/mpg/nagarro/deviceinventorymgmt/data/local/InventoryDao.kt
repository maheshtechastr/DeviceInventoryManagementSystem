package com.mpg.nagarro.deviceinventorymgmt.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity

@Dao
interface InventoryDao {
    /**
     *
     * ***************************Device Query**********************************
     *
     * */

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
     * Get list of Devices.
     *
     * @return all Devices.
     */
    @Query("SELECT * FROM DeviceEntity")
    fun getDevices(): List<DeviceEntity>

    /**
     * Observes list of Devices.
     *
     * @return all Devices.
     */
    @Query("SELECT * FROM DeviceEntity WHERE currentAvailableInventory <= totalInventory AND currentAvailableInventory > 0")
    fun observeAvailableDevices(): LiveData<List<DeviceEntity>>

    /**
     * Delete a Device by id.
     *
     * @return the number of devices deleted. This should always be 1.
     */
    @Query("DELETE FROM DeviceEntity WHERE deviceId = :deviceId")
    suspend fun deleteDeviceById(deviceId: Int): Int

    /**
     * Observes a single Device.
     *
     * @param deviceId the Device id.
     * @return the Device with deviceId.
     */
    @Query("SELECT * FROM DeviceEntity WHERE deviceId = :deviceId")
    fun observeDeviceById(deviceId: Int): LiveData<DeviceEntity>

    /**
     * Select a device by id.
     *
     * @param deviceId the device id.
     * @return the Device with deviceId.
     */
    @Query("SELECT * FROM DeviceEntity WHERE deviceId = :deviceId")
    suspend fun getDeviceById(deviceId: Int): DeviceEntity?

    /**
     * Update a device.
     *
     * @param deviceEntity Device to be updated
     * @return the number of Devices updated. This should always be 1.
     */
    @Update
    suspend fun updateDevice(deviceEntity: DeviceEntity): Int

    /**
     * Update a device.
     *
     * @param currentInventory Device currentInventory to be updated
     * @param deviceId Device id
     * @return the number of Devices updated. This should always be 1.
     */
    @Query("UPDATE DeviceEntity SET currentAvailableInventory = :currentInventory WHERE deviceId = :deviceId")
    suspend fun updateAvailableInventory(currentInventory: Int, deviceId: Int): Int

    /**
     * Update a device.
     *
     * @param totalInventory Device totalInventory to be updated
     * @param deviceId Device id
     * @return the number of Devices updated. This should always be 1.
     */
    @Query("UPDATE DeviceEntity SET totalInventory = :totalInventory WHERE deviceId = :deviceId")
    suspend fun updateTotalInventory(totalInventory: Int, deviceId: Int): Int

    /**
     *
     *
     * ***************************Employee Query**********************************
     *
     *
     * */

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
     * Get list of Employees.
     *
     * @return all Employees.
     */
    @Query("SELECT * FROM EmployeeEntity")
    fun getEmployees(): List<EmployeeEntity>

    /**
     * Delete a Employee by id.
     *
     * @return the number of Employees deleted. This should always be 1.
     */
    @Query("DELETE FROM EmployeeEntity WHERE empId = :empId")
    suspend fun deleteEmployeeById(empId: Int): Int

    /**
     * Observes a single Employee.
     *
     * @param empId the Employee id.
     * @return the Employee with empId.
     */
    @Query("SELECT * FROM EmployeeEntity WHERE empId = :empId")
    fun observeEmployeeById(empId: Int): LiveData<EmployeeEntity>

    /**
     * Get a single Employee.
     *
     * @param empId the Employee id.
     * @return the Employee with empId.
     */
    @Query("SELECT * FROM EmployeeEntity WHERE empId = :empId")
    suspend fun getEmployeeById(empId: Int): EmployeeEntity?

    /**
     *
     * ***************************DeviceInventory Query**********************************
     *
     * */

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
     * Get list of DeviceInventory.
     *
     * @return all DeviceInventory.
     */
    @Query("SELECT * FROM DeviceInventory")
    fun getDeviceInventories(): LiveData<List<DeviceInventory>>

    /**
     * Get a DeviceInventory by id.
     *@param recordId for recordId
     * @return the number of DeviceInventory deleted. This should always be 1.
     */
    @Query("SELECT * FROM DeviceInventory WHERE recordId = :recordId")
    suspend fun getDeviceInventoryById(recordId: Int): DeviceInventory?

    /**
     * Delete a DeviceInventory by id.
     *@param recordId for recordId
     * @return the number of DeviceInventory deleted. This should always be 1.
     */
    @Query("DELETE FROM DeviceInventory WHERE recordId = :recordId")
    suspend fun deleteDeviceInventoryById(recordId: Int): Int

    /**
     * Update the status of a DeviceInventory
     *
     * @param recordId    id of the Employee
     * @param status status to be updated
     */
    @Query("UPDATE DeviceInventory SET status = :status WHERE recordId = :recordId")
    suspend fun updateInventoryStatus(recordId: Int, status: Int): Int

    /**
     * Update the status of a DeviceInventory
     *
     * @param deviceInventory    id of the DeviceInventory
     */
    @Update
    suspend fun updateInventory(deviceInventory: DeviceInventory): Int

    /**
     * Delete all tasks.
     */
    @Query("DELETE FROM DeviceInventory")
    suspend fun deleteDeviceInventories()

    /**
     * Observes list of DeviceInventory.
     *Kindly Don't change these numeric value from Utils other wise some functionality may cause ambiguity
     * @return all DeviceInventory.
     */
    @Query("SELECT * FROM DeviceInventory WHERE empId = :empId AND (status = 2 OR status = 4) ")
    suspend fun getAllIssuedOrLostInventoryOfEmpId(empId: Int): List<DeviceInventory>
}